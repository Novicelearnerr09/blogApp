package com.sayaliblog.blogappapis.services;

import com.sayaliblog.blogappapis.payloads.CategoryDto;
import com.sayaliblog.blogappapis.repositories.CategoryRepo;

import java.util.List;

public interface CategoryService{
    //Create
    CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    //delete
    void deleteCategory(Integer categoryId);
    //get
     CategoryDto getCategory(Integer categoryId);
    //getAll
    List<CategoryDto> getAllCategories();
}
