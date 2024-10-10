package com.sayaliblog.blogappapis.controllers;

import com.sayaliblog.blogappapis.payloads.ApiResponse;
import com.sayaliblog.blogappapis.payloads.CategoryDto;
import com.sayaliblog.blogappapis.payloads.UserDto;
import com.sayaliblog.blogappapis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Category")
public class CategoryController {
    @Autowired
   private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {

        CategoryDto categoryDto1 = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }
    @PutMapping("/{categoryid}")
    public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryid") Integer categoryId)
    {
        CategoryDto updatedcategoryDto = this.categoryService.updateCategory(categoryDto, categoryId);
        return ResponseEntity.ok(updatedcategoryDto);
    }

    @DeleteMapping("/{categoryid}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("categoryid") Integer uid)
    {
        this.categoryService.deleteCategory(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllUser()
    {

        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }
    @GetMapping("/{categoryid}")
    public ResponseEntity<CategoryDto> getUserById(@PathVariable Integer categoryid)
    {
        return ResponseEntity.ok(this.categoryService.getCategory(categoryid));
    }

}
