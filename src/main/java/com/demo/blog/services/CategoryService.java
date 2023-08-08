package com.demo.blog.services;

import com.demo.blog.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer Id);
    public void deleteCategory(Integer Id);
    public CategoryDto getCategory(Integer Id);
    public List<CategoryDto> getCategories();
}
