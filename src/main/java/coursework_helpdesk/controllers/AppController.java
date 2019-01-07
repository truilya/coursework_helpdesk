package coursework_helpdesk.controllers;

import coursework_helpdesk.model.Role;
import coursework_helpdesk.model.User;
import coursework_helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coursework_helpdesk")
public class AppController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public Model welcomePage(Model model, Authentication authentication){
        String login = authentication.getName();
        User user = userRepository.findByLogin(login);
        List<Role> roles = user.getRolesList();
        List<String> stringRoles = new ArrayList<>();
        for(Role r: roles){
            stringRoles.add(r.toString());
        }
        model.addAttribute("roles",stringRoles);
        return model;
    }

}
