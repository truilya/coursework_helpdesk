package coursework_helpdesk.controllers;

import coursework_helpdesk.model.User;
import coursework_helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/coursework_helpdesk/user")
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public Model getAllUsers( Model model) {
        List<User> users = repository.findAll();
        model.addAttribute("users",users);
        return model;
    }

    @GetMapping("/add")
    public Model showForm(Model model){
        model.addAttribute("user",new User());
        return model;
    }
}
