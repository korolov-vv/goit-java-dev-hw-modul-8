package ua.goit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.goit.project.exceptions.UserAlreadyExistException;
import ua.goit.project.model.entity.User;
import ua.goit.project.service.MyService;
import ua.goit.project.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private MyService<User> service;

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping
    public String showUsers(Model model) {
        List<User> users = service.readAll();
        model.addAttribute("users", users);
        return "users/users";
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("userForm") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        try {
            service.create(user);
        } catch (UserAlreadyExistException ex) {
            model.addAttribute("message", "Account with provided email already exists");
            return "registration";
        }

        return "login";
    }

    @ModelAttribute("userForm")
    public User defaultUser() {
        return new User();
    }
}
