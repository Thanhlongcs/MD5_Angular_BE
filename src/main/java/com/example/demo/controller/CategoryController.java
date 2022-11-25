package com.example.demo.controller;

import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Category;
import com.example.demo.service.IUserService;
import com.example.demo.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IUserService userService;
    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping
    public ResponseEntity<?> getList() {
        List<Category> categoryList = categoryService.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
//        Iterable<Category> listCategories = categoryService.findAll();
//        return new ResponseEntity<>(listCategories, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<>(new ResponMessage("create success"), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailCategory(@PathVariable("id") Category category) {
        return category == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(category);
    }
    @PutMapping("/{id}")
public ResponseEntity<?>updateCategory(@PathVariable Long id, @RequestBody Category category){
Category category1 = categoryService.findById(id).get();
        category1.setName(category.getName());
        categoryService.save(category1);
        return new ResponseEntity<>(new ResponMessage("Update success!!!"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Category category = categoryService.findById(id).get();

        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("Delete Success!"), HttpStatus.OK);
    }

}
