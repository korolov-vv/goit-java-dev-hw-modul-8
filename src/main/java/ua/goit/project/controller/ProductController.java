package ua.goit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ua.goit.project.model.entity.Product;
import ua.goit.project.service.MyService;
import ua.goit.project.service.ProductService;

import java.util.List;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    MyService<Product> service;

    @Autowired
    public ProductController(ProductService productService) {
        this.service = productService;
    }

    @GetMapping
    public String showProducts(Model model) {
        List<Product> products = service.readAll();
        model.addAttribute("products", products);
        return "products/products";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/delete")
    public RedirectView delete(@RequestParam(name = "productName") String productName) {
        service.delete(productName);
        return new RedirectView("/products");
    }
}
