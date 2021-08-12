package ua.goit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.goit.project.model.entity.Manufacturer;
import ua.goit.project.service.ManufacturerService;
import ua.goit.project.service.MyService;

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

/*    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public String createOrUpdate(@Valid @ModelAttribute("entityForm") Manufacturer manufacturer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("entityForm", manufacturer);
            return "entityForm";
        }
        service.create(manufacturer);
        return "redirect:/manufacturers";
    }*/

/*    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "form/add")
    public <V> String showAddForm(Model model) {
        return formPage;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "form/update")
    public String showUpdateForm(@RequestParam(name = "userEmail") String userEmail, Model model) {
        T entity = getEntityService().read(userEmail).orElseThrow();
        model.addAttribute("entityForm", entity);
        setRelatedEntities(model);
        return formPage;
    }*/

/*    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/delete")
    public RedirectView delete(@RequestParam(name = "userEmail") String userEmail) {
        service.delete(userEmail);
        return new RedirectView(String.format("/manufacturers"));
    }*/
}
