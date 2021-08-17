package ua.goit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ua.goit.project.model.entity.Manufacturer;
import ua.goit.project.service.ManufacturerService;
import ua.goit.project.service.MyService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/manufacturers")
public class ManufacturerController {

    MyService<Manufacturer> service;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.service = manufacturerService;
    }

    @GetMapping
    public String showManufacturers(Model model) {
        List<Manufacturer> manufacturers = service.readAll();
        model.addAttribute("manufacturers", manufacturers);
        return "manufacturers/manufacturers";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/delete")
    public RedirectView delete(@RequestParam(name = "manufacturerName") String manufacturerName) {
        service.delete(manufacturerName);
        return new RedirectView("/manufacturers");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public String createOrUpdate(
            @Valid @ModelAttribute("manufacturerForm") Manufacturer manufacturer, BindingResult result, Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("manufacturerForm", manufacturer);
            return "manufacturers/manufacturerForm";
        }
        try {
            service.save(manufacturer);
        } catch (Exception ex) {
            model.addAttribute("message", ex.getMessage());
            return "users/userForm";
        }
        return "redirect:/manufacturers";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/form/add")
    public String showAddForm(Model model) {
        return "manufacturers/manufacturerForm";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/form/update")
    public String showUpdateForm(@RequestParam(name = "manufacturerName") String manufacturerName, Model model) {
        if (service.read(manufacturerName).isEmpty()) {
            model.addAttribute("message", String.format("----------The manufacturer with name: %s not found",
                    manufacturerName));
            return "redirect:error";
        }
        Manufacturer manufacturer = service.read(manufacturerName).get();
        model.addAttribute("manufacturer", manufacturer);
//        setRelatedEntities(model);
        return "manufacturers/updateManufacturerForm";
    }

    @ModelAttribute("manufacturerForm")
    public Manufacturer defaultManufacturer() {
        return new Manufacturer();
    }
}
