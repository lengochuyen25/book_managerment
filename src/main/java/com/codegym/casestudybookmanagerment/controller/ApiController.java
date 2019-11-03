package com.codegym.casestudybookmanagerment.controller;

import com.codegym.casestudybookmanagerment.model.Product;
import com.codegym.casestudybookmanagerment.model.ProductForm;
import com.codegym.casestudybookmanagerment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class ApiController {
    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/api", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> listMember(){
        List<Product> products = productService.findAll();
        System.out.println(products.size());
        if (products.isEmpty()){
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
    @ResponseBody
    @RequestMapping(value = "/api/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getMember(@PathVariable long id){
        Product product= productService.findById(id);
        if(product==null){
            return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/api/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteMember(@PathVariable long id){
        System.out.println(productService.findById(id));
        if (productService.findById(id)==null){
            return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
        }else {
            Product footballPlayer = productService.findById(id);
            productService.remove(id);
            return new ResponseEntity<Product>(footballPlayer, HttpStatus.OK);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/api/add" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addMember(@RequestBody Product product) {
        productService.add(product);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping(value = "/api/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateMember(@PathVariable("id") long id,
                                                       @RequestBody Product product) {
        Product originMember = productService.findById(id);

        if (originMember == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        originMember.setName(product.getName());
        originMember.setPrice(product.getPrice());
        originMember.setDescription(product.getDescription());


        productService.add(originMember);
        return new ResponseEntity<Product>(originMember, HttpStatus.OK);
    }

}
