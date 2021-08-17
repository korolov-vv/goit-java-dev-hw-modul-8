package ua.goit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ua.goit.project.model.entity.Manufacturer;
import ua.goit.project.model.entity.Product;
import ua.goit.project.service.ManufacturerService;
import ua.goit.project.service.MyService;
import ua.goit.project.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    MyService<Product> productService;
    MyService<Manufacturer> manufacturerService;

    @Autowired
    public ProductController(ProductService productService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String showProducts(Model model) {
        List<Product> products = productService.readAll();
        model.addAttribute("products", products);
        return "products/products";
    }

    @GetMapping(path = "findProduct")
    public String findProductForm() {
        return "products/findProductForm";
    }

    @GetMapping(path = "/product")
    public String displayProduct(@RequestParam(name = "productName") String productName, Model model) {
        if(productService.read(productName).isEmpty()){
            model.addAttribute("message",
                    String.format("The product with name %s not found", productName));
            return "products/findProductForm";
        }
        Product product = productService.read(productName).get();
        model.addAttribute(product);
        return "products/displayProduct";
    }

    @GetMapping(path = "/delete")
    public RedirectView delete(@RequestParam(name = "name") String productName) {
        productService.delete(productName);
        return new RedirectView("/products");
    }

    @PostMapping
    public String createOrUpdate(
            @Valid @ModelAttribute("productForm") Product product, BindingResult result, Model model, @RequestParam("manufacturerName") String manufacturerName
    ) {
        if (result.hasErrors()) {
            model.addAttribute("productForm", product);
            return "products/productForm";
        }
            Manufacturer manufacturer = null;
            if(manufacturerService.read(manufacturerName).isPresent()) {
                manufacturer = manufacturerService.read(manufacturerName).get();
            }
            product.setManufacturer(manufacturer);
        try {
            productService.save(product);
        } catch (Exception ex) {
            model.addAttribute("message", ex.getMessage());
            return "products/productForm";
        }
        return "redirect:/products";
    }

    @GetMapping(path = "form/add")
    public String showAddForm(Model model) {
        return "products/productForm";
    }

    @GetMapping(path = "/form/update")
    public String showUpdateForm(@RequestParam(name = "name") String productName, Model model) {
        if (productService.read(productName).isEmpty()) {
            model.addAttribute("message", String.format("----------The product with name: %s not found",
                    productName));
            return "redirect:error";
        }
        Product product = productService.read(productName).get();
        model.addAttribute("product", product);
        return "products/updateProductForm";
    }

    @ModelAttribute("productForm")
    public Product defaultProduct() {
        return new Product();
    }
}
