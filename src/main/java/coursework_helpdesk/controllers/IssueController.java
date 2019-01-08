package coursework_helpdesk.controllers;

import coursework_helpdesk.model.*;
import coursework_helpdesk.repository.*;
import coursework_helpdesk.util.Dates;
import coursework_helpdesk.util.Listes;
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
        IssueFilter issueFilter = new IssueFilter();
        model.addAttribute("issueFilter",issueFilter);
        return model;
    }
    
    @PostMapping("/list")
    public Model getFilteredIssue(Model model,
                                   @RequestParam(value = "startDate",required = false) String startDate,
                                   @RequestParam(value = "endDate",required = false) String endDate,
                                   @RequestParam(value = "issueStatuses",required = false) List<IssueStatus> issueStatusList,
                                   @RequestParam(value = "issuePriorities",required = false) List<IssuePriority> issuePriorityList,
                                   @RequestParam(value = "creators",required = false) List<User> creatorList,
                                   @RequestParam(value = "engineers",required = false) List<User> engineerList){
        List<Integer> statuses = Listes.getListInteger(issueStatusList);
        List<Integer> priorities = Listes.getListInteger(issuePriorityList);
        List<Integer> creators = Listes.getListInteger(creatorList);
        List<Integer> engineers = Listes.getListInteger(engineerList);
        String localStartDate = "".equals(startDate) ?"2000-01-01" :startDate;
        String localEndDate = "".equals(endDate) ?"2099-01-01" :endDate;
        List<Issue> filteredIssue = repository.findByFilter(localStartDate,
                localEndDate,
                statuses,
                priorities,
                creators,
                engineers);
        model.addAttribute("issues",filteredIssue);
        IssueFilter issueFilter = new IssueFilter();
        model.addAttribute("issueFilter",issueFilter);
        return model;
    }

    @GetMapping("/add")
    public Model showForm(Model model){
        model.addAttribute("issue",new Issue());
        return model;
    }

    @PostMapping("/edit")
    public Model edit(@RequestParam("id") int id, Model model){
        setModelAttrForEditAndShow(id, model);
        return model;
    }

    @PostMapping("/show")
    public Model show(@RequestParam("id") int id, Model model){
        setModelAttrForEditAndShow(id, model);
        return model;
    }

    private void setModelAttrForEditAndShow(int id, Model model){
        Issue issue = repository.findOne(id);
        model.addAttribute("issue",issue);
        List<IssueHistory> issueHistoryList = issueHistoryRepository.findByIssueIdOrderByIdDesc(issue.getId());
        model.addAttribute("issueHistoryList",issueHistoryList);

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

    @ModelAttribute("allIssueStatusList")
    public List<IssueStatus> getAllStatusList(){
        List<IssueStatus> statuses = statusRepository.findAll();
        return statuses;
    }

    @ModelAttribute("engineerList")
    public List<User> getEngineerList(){
        return userRepository.findAllUserByRole(Role.ENGINEER.name());
    }

    @ModelAttribute("userList")
    public List<User> getUserList(){
        return userRepository.findAll();
    }

}
