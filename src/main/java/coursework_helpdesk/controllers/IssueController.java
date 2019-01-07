package coursework_helpdesk.controllers;

import coursework_helpdesk.model.*;
import coursework_helpdesk.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/coursework_helpdesk/issue")
public class IssueController {

    private IssueRepository repository;
    private IssuePriorityRepository priorityRepository;
    private IssueStatusRepository statusRepository;
    private UserRepository userRepository;
    private IssueHistoryRepository issueHistoryRepository;

    @Autowired
    public IssueController(IssueRepository repository,
                           IssuePriorityRepository priorityRepository,
                           IssueStatusRepository statusRepository,
                           UserRepository userRepository,
                           IssueHistoryRepository issueHistoryRepository) {
        this.repository = repository;
        this.priorityRepository = priorityRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.issueHistoryRepository = issueHistoryRepository;
    }

    @GetMapping("/list")
    public Model getAllIssue(Model model){
        List<Issue> issues = repository.findAll();
        model.addAttribute("issues",issues);
        return model;
    }

    @GetMapping("/add")
    public Model showForm(Model model){
        model.addAttribute("issue",new Issue());
        return model;
    }

    @PostMapping("/edit")
    public Model edit(@RequestParam("id") int id, Model model){
        Issue issue = repository.findOne(id);
        model.addAttribute("issue",issue);
        return model;
    }

    @PostMapping("/save")
    public String save(Issue issue,BindingResult bindingResult, Authentication authentication){
        if (bindingResult.hasErrors()){
            return "/list";
        }
        issue.setDateCreated(LocalDateTime.now());
        IssueStatus issueStatus = statusRepository.findOne(1); //sorry :'(
        issue.setIssueStatus(issueStatus);
        String creatorLogin = authentication.getName();
        User creator = userRepository.findByLogin(creatorLogin);
        issue.setCreator(creator);
        repository.save(issue);
        IssueHistory issueHistory = new IssueHistory(issue,"",creator);
        issueHistoryRepository.save(issueHistory);
        return "redirect:list";
    }

    @PostMapping("/saveUpdate")
    public String saveUpdate(Issue issue, BindingResult bindingResult, Authentication authentication, @RequestParam("comment") String comment){
        if (bindingResult.hasErrors()){
            return "/list";
        }
        repository.save(issue);
        String creatorLogin = authentication.getName();
        User changer = userRepository.findByLogin(creatorLogin);
        IssueHistory issueHistory = new IssueHistory(issue,comment,changer);
        issueHistoryRepository.save(issueHistory);
        return "redirect:list";
    }

    @ModelAttribute("issuePriorityList")
    public List<IssuePriority> getPriorityList(){
        List<IssuePriority> priorities = priorityRepository.findAll();
        return priorities;
    }

    @ModelAttribute("issueStatusList")
    public List<IssueStatus> getStatusList(Issue issue, Authentication authentication){
        Issue currentIssue = null;
        Integer currentIssueStatusId = 0;
        if (issue != null && issue.getId()!=null){
            currentIssue = repository.findOne(issue.getId());
            currentIssueStatusId = currentIssue.getIssueStatus().getId();
        }
        String login = authentication.getName();
        User user = userRepository.findByLogin(login);
        List<Role> roles = user.getRolesList();
        List<IssueStatus> statuses = statusRepository.findAllByRoles(currentIssueStatusId, roles.stream().map(Role::toString).collect(Collectors.toList()));
        return statuses;
    }

    @ModelAttribute("engineerList")
    public List<User> getEngineerList(){
        return userRepository.findAllUserByRole(Role.ENGINEER.name());
    }

}
