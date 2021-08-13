package ua.goit.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ua.goit.project.exceptions.ObjectAlreadyExistException;
import ua.goit.project.model.entity.User;
import ua.goit.project.service.MyService;
import ua.goit.project.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/delete")
    public RedirectView delete(@RequestParam(name = "userEmail") String userEmail) {
        service.delete(userEmail);
        return new RedirectView("/users");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public String createOrUpdate(@Valid @ModelAttribute("userForm") User user, BindingResult result, Model model) {
        LOGGER.info("------------THE USER TO CREATE IS:" + user.toString());
        if (result.hasErrors()) {
            model.addAttribute("userForm", user);
            return "users/userForm";
        }
        service.create(user);
        return "redirect:/users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "form/add")
    public String showUserForm() {
        return "users/userForm";
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
