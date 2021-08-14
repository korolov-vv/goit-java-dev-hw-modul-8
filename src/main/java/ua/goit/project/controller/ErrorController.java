package ua.goit.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/error")
public class ErrorController {
    @GetMapping(path = "error")
    public String error(Model model, String error) {
        model.addAttribute("error", error);
        return "error";
    }
}
