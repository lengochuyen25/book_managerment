package com.codegym.casestudybookmanagerment.service.impl;

import com.codegym.casestudybookmanagerment.model.Product;
import com.codegym.casestudybookmanagerment.model.ProductForm;
import com.codegym.casestudybookmanagerment.repository.ProductRepository;
import com.codegym.casestudybookmanagerment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class ProductServiceImpl implements ProductService {
    @Autowired
    Environment env;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void save(ProductForm productForm) {
//1. store img to hask disk;

        //2. store url, info of product to database

        Product product = exchangeObject(productForm); //

        productRepository.save(product);
    }

    @Override
    public void add(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Product exchangeObject(ProductForm productForm) {
        String fileName;
        if (productForm.getImg().equals("")) {
            fileName = productRepository.findById(productForm.getId()).get().getImg();
        } else {
            MultipartFile multipartFile = productForm.getImg();
            fileName = multipartFile.getOriginalFilename();
        }
        String folderContaintFileStore = env.getProperty("file_upload");
        // /home/huyen/IdeaProjects/ListMVC/MVC_Product_managerment/src/main/webapp/WEB-INF/views/images/
        try {
            // write file to hard disk;
            FileCopyUtils.copy(productForm.getImg().getBytes(), new File(folderContaintFileStore + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // colect info for store to database;
        Product product = new Product(productForm.getId(), productForm.getName(),
                productForm.getPrice(), fileName,productForm.getDescription(), productForm.getAuthor());
        return product;
    }

    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining(name, pageable);
    }
}
