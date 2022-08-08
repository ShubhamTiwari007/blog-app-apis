package com.blog.app.services;

import com.blog.app.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

    void deleteCategory(Integer id);

    CategoryDto getCategory(Integer id);

    List<CategoryDto> getCategories();
}
