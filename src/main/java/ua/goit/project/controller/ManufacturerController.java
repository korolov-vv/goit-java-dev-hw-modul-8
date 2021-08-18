package ua.goit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ua.goit.project.model.entity.Manufacturer;
import ua.goit.project.service.ManufacturerService;
import ua.goit.project.service.IService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/manufacturers")
public class ManufacturerController {

    IService<Manufacturer> service;

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

    @GetMapping(path = "findManufacturer")
    public String findManufacturerForm() {
        return "manufacturers/findManufacturerForm";
    }

    @GetMapping(path = "/manufacturer")
    public String displayManufacturer(@RequestParam(name = "manufacturerName") String manufacturerName, Model model) {
        if(service.read(manufacturerName).isEmpty()){
            model.addAttribute("message",
                    String.format("The Manufacturer with name %s not found", manufacturerName));
            return "manufacturers/findManufacturerForm";
        }
        Manufacturer manufacturer = service.read(manufacturerName).get();
        model.addAttribute(manufacturer);
        return "manufacturers/displayManufacturer";
    }

    @GetMapping(path = "/delete")
    public RedirectView delete(@RequestParam(name = "name") String manufacturerName) {
        service.delete(manufacturerName);
        return new RedirectView("/manufacturers");
    }

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
            return "manufacturers/manufacturerForm";
        }
        return "redirect:/manufacturers";
    }

    @GetMapping(path = "/form/add")
    public String showAddForm(Model model) {
        return "manufacturers/manufacturerForm";
    }

    @GetMapping(path = "/form/update")
    public String showUpdateForm(@RequestParam(name = "name") String manufacturerName, Model model) {
        if (service.read(manufacturerName).isEmpty()) {
            model.addAttribute("message", String.format("----------The manufacturer with name: %s not found",
                    manufacturerName));
            return "redirect:error";
        }
        Manufacturer manufacturer = service.read(manufacturerName).get();
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturers/updateManufacturerForm";
    }

    @ModelAttribute("manufacturerForm")
    public Manufacturer defaultManufacturer() {
        return new Manufacturer();
    }
}
