package com.codegym.casestudybookmanagerment.controller;

import com.codegym.casestudybookmanagerment.model.Author;
import com.codegym.casestudybookmanagerment.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public ModelAndView listAuthors() {
        Iterable<Author> authors = authorService.findAll();
        ModelAndView modelAndView = new ModelAndView("/author/list");
        modelAndView.addObject("authors", authors);
        return modelAndView;
    }

    @GetMapping("/create-author")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/author/create");
        modelAndView.addObject("author", new Author());
        return modelAndView;
    }


    @PostMapping("/create-author")
    public ModelAndView saveProvince(@ModelAttribute("author") Author author) {
        authorService.save(author);

        ModelAndView modelAndView = new ModelAndView("/author/create");
        modelAndView.addObject("author", new Author());
        modelAndView.addObject("message", "New author created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-author/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        if (author != null) {
            ModelAndView modelAndView = new ModelAndView("/author/edit");
            modelAndView.addObject("author", author);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-author")
    public ModelAndView updateProvince(@ModelAttribute("author") Author author) {
        authorService.save(author);
        ModelAndView modelAndView = new ModelAndView("/author/edit");
        modelAndView.addObject("author", author);
        modelAndView.addObject("message", "Author updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-author/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Optional<Author> author = authorService.findById(id);
        if(author != null) {
            ModelAndView modelAndView = new ModelAndView("/author/delete");
            modelAndView.addObject("author", author);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-author")
    public String deleteAuthor(@ModelAttribute("author") Author author){
        authorService.remove(author.getId());
        return "redirect:authors";
    }



}
