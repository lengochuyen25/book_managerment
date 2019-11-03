package com.codegym.casestudybookmanagerment.controller;


import com.codegym.casestudybookmanagerment.model.Author;
import com.codegym.casestudybookmanagerment.model.Product;
import com.codegym.casestudybookmanagerment.service.AuthorService;
import com.codegym.casestudybookmanagerment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductCustomerController {
    @Autowired
    private ProductService productService;

    @Autowired
    private AuthorService authorService;
    @ModelAttribute("authors")
    public Iterable<Author> authors() {
        return authorService.findAll();
    }

    @GetMapping("/products_customer")
    public ModelAndView listProductsCustomer(@RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Product> products;
        if(s.isPresent()){
            products=productService.findAllByNameContaining(s.get(),pageable);
        }else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product_customer/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/view-product-customer/{id}")
    public ModelAndView viewProductCustomer(@PathVariable Long id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product_customer/view");
        modelAndView.addObject("product", product);
        return modelAndView;
    }
}
