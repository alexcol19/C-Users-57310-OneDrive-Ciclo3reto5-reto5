package com.example.reto5.Controllers;

import com.example.reto5.Services.CategoryService;
import com.example.reto5.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Category")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public List<Category> getCategory(){
        return categoryService.getAll(); //getAll//
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategory(@PathVariable("id") int id){
        return categoryService.getId(id); //getCategory//
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Category saveCategory(@RequestBody Category category){
        return categoryService.save(category); //save//
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Category update(@RequestBody Category category){

        return categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return categoryService.deleteCategory(id);
    }

}

