package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    void deleteCategoryById(Integer id);

    CategoryResponse editCategoryById(Integer id, CategoryRequest request);

    void createNewCategory(CategoryRequest request);

    CategoryResponse findCategoryById(Integer id);
    CategoryResponse findCategoryByName(String name);
    List<CategoryResponse> findCategories();
}
