package com.example.reto5.Services;

import com.example.reto5.Repository.CategoryRepository;
import com.example.reto5.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getId(int id) {
        return categoryRepository.getCategory(id);
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            return categoryRepository.save(category);
        } else {
            Optional<Category> paux = categoryRepository.getCategory(category.getId());
            if (paux.isEmpty()) {
                return categoryRepository.save(category);
            } else
                return category;
        }
    }
    public Category update(Category category){
        if(category.getId() != null){
            Optional<Category> paux = categoryRepository.getCategory(category.getId());
            if(!paux.isEmpty()){
                if (category.getDescription() != null){
                    paux.get().setDescription(category.getDescription());
                }
                if (category.getName() != null){
                    paux.get().setName(category.getName());
                }
                return categoryRepository.save(paux.get());
            }
        }
        return category;
    }
    public boolean deleteCategory(int id){
        Boolean r = getId(id).map(category ->{
            categoryRepository.delete(category);
            return true;
        }).orElse(false);


        return r;
    }

}
