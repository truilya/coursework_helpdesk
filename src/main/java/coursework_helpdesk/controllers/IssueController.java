package coursework_helpdesk.controllers;

import coursework_helpdesk.model.*;
import coursework_helpdesk.repository.IssuePriorityRepository;
import coursework_helpdesk.repository.IssueRepository;
import coursework_helpdesk.repository.IssueStatusRepository;
import coursework_helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coursework_helpdesk/issue")
public class IssueController {

    private IssueRepository repository;
    private IssuePriorityRepository priorityRepository;
    private IssueStatusRepository statusRepository;
    private UserRepository userRepository;

    @Autowired
    public IssueController(IssueRepository repository,
                           IssuePriorityRepository priorityRepository,
                           IssueStatusRepository statusRepository,
                           UserRepository userRepository) {
        this.repository = repository;
        this.priorityRepository = priorityRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
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
        return "redirect:list";
    }

    @PostMapping("/saveUpdate")
    public String saveUpdate(Issue issue, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/list";
        }
        Issue oldIssue = repository.findOne(issue.getId());
        if (issue.getIssueStatus()==null || issue.getIssueStatus().getId()==-1){
            issue.setIssueStatus(oldIssue.getIssueStatus());
        }
        repository.save(issue);
        return "redirect:list";
    }

    @ModelAttribute("issuePriorityList")
    public List<IssuePriority> getPriorityList(){
        List<IssuePriority> priorities = priorityRepository.findAll();
        return priorities;
    }

    @ModelAttribute("issueStatusList")
    public List<IssueStatus> getStatusList(Authentication authentication){
        String login = authentication.getName();
        User user = userRepository.findByLogin(login);
        List<Role> roles = user.getRolesList();
        List<IssueStatus> statuses = statusRepository.findAll();
        List<IssueStatus> statusResult = new ArrayList<>();
        for (IssueStatus s: statuses){
            for (Role r : roles){
                if (!s.isRoleEmpty() && s.getRole().compareTo(r)==0){
                    statusResult.add(s);
                }
            }
        }
        IssueStatus noValueStatus = new IssueStatus();
        noValueStatus.setId(-1);
        noValueStatus.setName("Без изменений");
        statusResult.add(noValueStatus);
        return statusResult;
    }


}
