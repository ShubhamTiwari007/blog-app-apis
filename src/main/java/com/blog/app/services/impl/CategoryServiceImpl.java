package com.blog.app.services.impl;

import com.blog.app.entities.Category;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.CategoryDto;
import com.blog.app.repositries.CategoryRepo;
import com.blog.app.services.CategoryService;
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

    private Category dtoToCategory(CategoryDto categoryDto){
        return this.modelMapper.map(categoryDto,Category.class);
    }

    private CategoryDto categoryToDto(Category category){
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = dtoToCategory(categoryDto);
        Category saveCategory = this.categoryRepo.save(category);
        return categoryToDto(saveCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        return categoryToDto(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",id));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",id));
        return categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categoryList = this.categoryRepo.findAll();
        return categoryList.stream().map( category -> categoryToDto(category)).collect(Collectors.toList());
    }
}
