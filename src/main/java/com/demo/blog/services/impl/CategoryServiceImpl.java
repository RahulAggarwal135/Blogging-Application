package com.demo.blog.services.impl;

import com.demo.blog.entities.Category;
import com.demo.blog.exceptions.ResourceNotFoundException;
import com.demo.blog.payloads.CategoryDto;
import com.demo.blog.repositories.CategoryRepo;
import com.demo.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto,Category.class);
        Category saved = this.categoryRepo.save(category);
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer Id) {
        try {
            Category cat = this.categoryRepo.findById(Id).get();
            cat.setCategoryDescription(categoryDto.getCategoryDescription());
            cat.setCategoryTitle(categoryDto.getCategoryTitle());
            Category updatedcat = this.categoryRepo.save(cat);
            return this.modelMapper.map(updatedcat, CategoryDto.class);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Category", "Id", Id);
        }
    }

    @Override
    public void deleteCategory(Integer Id) {
        try{
            this.categoryRepo.deleteById(Id);
        }
        catch(Exception e) {
            throw new ResourceNotFoundException("category", "ID", Id);
        }

    }

    @Override
    public CategoryDto getCategory(Integer Id) {
        try {
            Category category = this.categoryRepo.findById(Id).get();
            return this.modelMapper.map(category, CategoryDto.class);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Category", "Id", Id);
        }
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();
        for (Category temp : categories) {
            CategoryDto categoryDto  = this.modelMapper.map(temp, CategoryDto.class);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;

    }
}
