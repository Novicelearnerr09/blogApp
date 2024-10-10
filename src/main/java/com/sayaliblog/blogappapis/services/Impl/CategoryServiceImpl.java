package com.sayaliblog.blogappapis.services.Impl;

import com.sayaliblog.blogappapis.entities.Category;
import com.sayaliblog.blogappapis.entities.User;
import com.sayaliblog.blogappapis.exceptions.ResourceNotFoundException;
import com.sayaliblog.blogappapis.payloads.CategoryDto;
import com.sayaliblog.blogappapis.payloads.UserDto;
import com.sayaliblog.blogappapis.repositories.CategoryRepo;
import com.sayaliblog.blogappapis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
       Category cat =this.modelMapper.map(categoryDto, Category.class);
       Category addedCat=this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryid",categoryId));
//        cat.setCategoryId(categoryDto.getCategoryId());
   cat.setCategoryTitle(categoryDto.getCategoryTitle());
 cat.setCategoryDescription(categoryDto.getCategoryDescription());

        Category savedcategory=this.categoryRepo.save(cat);
        CategoryDto updateCategory=this.modelMapper.map(savedcategory, CategoryDto.class);
        return updateCategory;
    }

    private CategoryDto CategorytocategoryDto(Category category) {

        CategoryDto category1=this.modelMapper.map(category,CategoryDto.class);
        return category1;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {

        Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryid",categoryId));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> category = this.categoryRepo.findAll();
       List<CategoryDto> categoryDtos= category.stream().map((cate)->this.modelMapper.map(cate,CategoryDto.class)).collect(Collectors.toList());
       return categoryDtos;

    }
}
