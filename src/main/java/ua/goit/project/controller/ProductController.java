package ua.goit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
