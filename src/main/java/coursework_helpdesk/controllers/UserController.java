package coursework_helpdesk.controllers;

import coursework_helpdesk.model.User;
import coursework_helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/coursework_helpdesk/users")
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        return repository.findAll();
    }
}
