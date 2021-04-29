package com.example.demo.controllers;

import com.example.demo.jpa.entities.CategoryEntity;
import com.example.demo.jpa.repositories.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryService
{
    private final CategoryJpaRepository _categoryJpaRepository;

    public CategoryService(CategoryJpaRepository _categoryJpaRepository) {
        this._categoryJpaRepository = _categoryJpaRepository;
    }


    //----------Retrieve Categories-------------

    @GetMapping(path = "/mysql")
    public ResponseEntity<?> getCategoryFromMysql(@RequestParam(value = "name") String name)
    {
        List<CategoryEntity> categoryEntityList = _categoryJpaRepository.findAllByName(name);
        if (!categoryEntityList.isEmpty())
        {
            return new ResponseEntity<>(categoryEntityList, HttpStatus.OK);
        }
        System.out.println("There isn't any Category in MySQL database with name: " + name);

        return new ResponseEntity<>(new StringBuilder("There isn't any Category in MySQL database with name: ").append(name).toString(), HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/all/mysql")
    public List<CategoryEntity> getAllCategoriesFromMysql()
    {
        return _categoryJpaRepository.findAll();
    }


    //----------Create a Category---------------

    @PostMapping(path = "/mysql")
    public Object addNewCategoryInMysql(@RequestParam(value = "name") String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            return HttpStatus.BAD_REQUEST;
        }
        CategoryEntity createdCategoryEntity = new CategoryEntity(name.trim());
        createdCategoryEntity = _categoryJpaRepository.save(createdCategoryEntity);
        System.out.println("A new Category created in MySQL database with id: " + createdCategoryEntity.getId() + "  and name: " + createdCategoryEntity.getName());
        return createdCategoryEntity;
    }


    //----------Update a Category---------------

    @PutMapping(path = "/mysql")
    public ResponseEntity<String> updateCategoryInMysql(@Valid @RequestBody CategoryEntity category)
    {
        if (category == null)
        {
            return new ResponseEntity<>("Your request is null!", HttpStatus.BAD_REQUEST);
        }
        try
        {
            CategoryEntity categoryEntity = _categoryJpaRepository.findById(category.getId()).orElseThrow(EntityNotFoundException::new);
            categoryEntity.setName(category.getName());
            _categoryJpaRepository.save(categoryEntity);
            return new ResponseEntity<>("The category updated", HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>("This category does not exists", HttpStatus.NOT_FOUND);
        }
    }
}
