package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.ProductDetail;
import com.example.demo.service.ProductService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
    
        // วนลูปคำนวณราคาหลังหักส่วนลดด้วย Strategy ผ่าน ProductService
        for (Product product : products) {
            double discounted = productService.calculateEffectivePrice(product);
            product.setDiscountedPrice(discounted);
        }
    
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Product product = new Product();
        product.setDetail(new ProductDetail());
        model.addAttribute("product", product);
        return "products/add";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product.getDetail() == null) {
            product.setDetail(new ProductDetail());
        }
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteConfirm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "products/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}