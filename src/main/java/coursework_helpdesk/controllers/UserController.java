package coursework_helpdesk.controllers;

import coursework_helpdesk.model.User;
import coursework_helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/edit")
    public Model edit(@RequestParam("id") int id, Model model){
        User user = repository.findOne(id);
        model.addAttribute("user",user);
        return model;
    }

    @PostMapping("/save")
    public String save( User user){
        repository.save(user);
        return "redirect:list";
    }
}
