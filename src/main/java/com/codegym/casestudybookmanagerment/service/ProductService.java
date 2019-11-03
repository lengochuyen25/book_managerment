package com.codegym.casestudybookmanagerment.service;

import com.codegym.casestudybookmanagerment.model.Product;
import com.codegym.casestudybookmanagerment.model.ProductForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface  ProductService {
    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    void save(ProductForm productForm);

    void add(Product product);

    void remove(Long id);

    Product exchangeObject(ProductForm productForm);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);
}
