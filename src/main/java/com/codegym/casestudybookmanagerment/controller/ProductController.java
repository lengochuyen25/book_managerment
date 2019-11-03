package com.codegym.casestudybookmanagerment.controller;

import com.codegym.casestudybookmanagerment.model.Author;
import com.codegym.casestudybookmanagerment.model.Product;
import com.codegym.casestudybookmanagerment.model.ProductForm;
import com.codegym.casestudybookmanagerment.service.AuthorService;
import com.codegym.casestudybookmanagerment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller

public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private AuthorService authorService;

    @ModelAttribute("authors")
    public Iterable<Author> authors(){

        return authorService.findAll();
    }


    @GetMapping("/products")
    public ModelAndView listProducts(@RequestParam("s") Optional<String> s, Pageable pageable) {

        Page<Product> products;
        if(s.isPresent()){
            products=productService.findAllByNameContaining(s.get(),pageable);
        }else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView saveProduct(@ModelAttribute("product") ProductForm productForm, BindingResult result) {
        if(result.hasFieldErrors()){
            return new ModelAndView("/product/error.404");
        }
        productService.save(productForm);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }


    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView(("/error.404"));
            return modelAndView;
        }
    }
    @PostMapping("/edit-product")
    public ModelAndView updateCustomer(@ModelAttribute("product") ProductForm productForm) {
        productService.save(productForm);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", productService.exchangeObject(productForm));
        modelAndView.addObject("message", "Product update successfully");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/product/error.404");
            return modelAndView;
        }

    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:products";
    }


    @GetMapping("/view-product/{id}")
    public ModelAndView viewProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/view");
        modelAndView.addObject("product", product);
        return modelAndView;
    }






}
