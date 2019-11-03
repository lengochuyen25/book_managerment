package com.codegym.casestudybookmanagerment.model;


import org.springframework.web.multipart.MultipartFile;


public class ProductForm {

    private Long id;
    private String name;
    private String price;
    private MultipartFile img;
    private String description;

    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }



    public ProductForm() {
    }

    public ProductForm(Long id, String name, String price, MultipartFile img, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.description = description;

    }

    public ProductForm(Long id, String name, String price, MultipartFile img, String description, Author author) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.description = description;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

