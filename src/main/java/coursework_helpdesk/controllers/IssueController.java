package coursework_helpdesk.controllers;

import coursework_helpdesk.model.Issue;
import coursework_helpdesk.model.IssuePriority;
import coursework_helpdesk.model.IssueStatus;
import coursework_helpdesk.model.User;
import coursework_helpdesk.repository.IssuePriorityRepository;
import coursework_helpdesk.repository.IssueRepository;
import coursework_helpdesk.repository.IssueStatusRepository;
import coursework_helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
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

    @PostMapping("/save")
    public String save(Issue issue,BindingResult bindingResult, Authentication authentication){
        if (bindingResult.hasErrors()){
            return "/list";
        }
        issue.setDateCreated(LocalDateTime.now());
        IssueStatus issueStatus = statusRepository.findOne(1); //sorry :'(
        issue.setIssueStatus(issueStatus);
        String creatorLogin = authentication.getName();
        User creator = userRepository.findByLogin(creatorLogin); //костыль
        issue.setCreator(creator);
        repository.save(issue);
        return "redirect:list";
    }

    @ModelAttribute("issuePriorityList")
    public List<IssuePriority> getRoleList(){
        List<IssuePriority> priorities = priorityRepository.findAll();
        return priorities;
    }
}
