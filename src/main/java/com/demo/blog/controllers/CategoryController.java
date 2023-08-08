package com.demo.blog.controllers;

import com.demo.blog.payloads.CategoryDto;
import com.demo.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createcategory (@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createCategorydto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategorydto, HttpStatus.CREATED);

    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory (@Valid @RequestBody CategoryDto categoryDto, @PathVariable ("categoryId") Integer Id) {
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto,Id);
        return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.OK);

    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory (@PathVariable ("categoryId") Integer Id) {
        this.categoryService.deleteCategory(Id);
        return new ResponseEntity(Map.of("message", "Category deleted"), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
    }

}
