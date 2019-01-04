package coursework_helpdesk.controllers;

import coursework_helpdesk.model.Role;
import coursework_helpdesk.model.User;
import coursework_helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id){
        repository.delete(id);
        return "redirect:list";
    }

    @PostMapping("/save")
    public String save( User user){
        if (!user.isNew()) {
            User oldUser = repository.findOne(user.getId());
            if (!oldUser.getPassword().equals(user.getPassword())){
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            }
        }
        else {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        repository.save(user);
        return "redirect:list";
    }

    @ModelAttribute("roleList")
    public List<String> getRoleList(){
        List<String> roles = new ArrayList<>();
        for (Role r : Role.values()){
            roles.add(r.toString());
        }
        return roles;
    }
}
