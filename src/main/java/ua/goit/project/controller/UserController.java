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
import ua.goit.project.model.entity.User;
import ua.goit.project.service.MyService;
import ua.goit.project.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;
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

    @GetMapping(path = "findUser")
    public String findUserForm() {
        return "users/findUserForm";
    }

    @GetMapping(path = "/user")
    public String displayUser(@RequestParam(name = "userEmail") String userEmail, Model model) {
        if (service.read(userEmail).isEmpty()) {
            model.addAttribute("message",
                    String.format("The User with email %s not found", userEmail));
            return "users/findUserForm";
        }
        User user = service.read(userEmail).get();
        model.addAttribute(user);
        return "users/displayUser";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/delete")
    public RedirectView delete(@RequestParam(name = "email") String userEmail) {
        try {
            service.delete(userEmail);
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage() + ex.getCause() + Arrays.toString(ex.getStackTrace()));
        }
        return new RedirectView("/users");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public String createOrUpdate(@Valid @ModelAttribute("userForm") User user, BindingResult result, Model model) {
        LOGGER.debug("------------THE USER TO CREATE OR UPDATE IS:" + user.toString());
        if (result.hasErrors()) {
            model.addAttribute("userForm", user);
            return "users/userForm";
        }
        try {
            service.save(user);
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage() + ex.getCause() + Arrays.toString(ex.getStackTrace()));
            model.addAttribute("message", ex.getMessage());
            return "users/userForm";
        }
        return "redirect:/users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "form/add")
    public String createUserForm() {
        return "users/userForm";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "form/update")
    public String updateUserForm(@RequestParam(name = "email") String email, Model model) {
        if (service.read(email).isEmpty()) {
            model.addAttribute("message", String.format("----------The user with email: %s not found", email));
            return "redirect:error";
        }
        User user = service.read(email).get();
        model.addAttribute("user", user);
        return "users/updateUserForm";
    }

    @ModelAttribute("userForm")
    public User defaultUser() {
        return new User();
    }
}
