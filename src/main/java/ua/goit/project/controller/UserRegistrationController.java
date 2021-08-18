package ua.goit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.goit.project.exceptions.ObjectAlreadyExistException;
import ua.goit.project.model.entity.User;
import ua.goit.project.service.IService;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserRegistrationController {
    private IService<User> service;

    @Autowired
    public UserRegistrationController(IService<User> service) {
        this.service = service;
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
            service.save(user);
        } catch (ObjectAlreadyExistException ex) {
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
