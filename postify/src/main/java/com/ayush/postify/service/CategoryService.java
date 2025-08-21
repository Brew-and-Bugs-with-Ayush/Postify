package com.ayush.postify.service;

import com.ayush.postify.domain.dtos.CategoryDto;
import com.ayush.postify.domain.dtos.CreateCategoryRequestDto;
import com.ayush.postify.domain.entity.Category;
import com.ayush.postify.mappers.CategoryMapper;
import com.ayush.postify.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getCategories() {
        return categoryRepository.findAllWithPostCount()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Transactional
   public CategoryDto createCategory(CreateCategoryRequestDto createCategoryRequestDto){
       Category entity = categoryMapper.toEntity(createCategoryRequestDto);

       if (categoryRepository.existsByNameIgnoreCase(entity.getName())){
           throw new IllegalArgumentException("Category already exists with name" + entity.getName());
       }
       Category save = categoryRepository.save(entity);

       return categoryMapper.toDto(save);
   }

    @Transactional
    public CategoryDto updateCategory(CreateCategoryRequestDto createCategoryRequestDto, Long id) {

        // Find existing category
        Category entity = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id " + id));

        if (categoryRepository.existsByNameIgnoreCase(createCategoryRequestDto.getName())
                && !entity.getName().equalsIgnoreCase(createCategoryRequestDto.getName())) {
            throw new IllegalArgumentException("Category already exists with name " + createCategoryRequestDto.getName());
        }

        entity.setName(createCategoryRequestDto.getName());

        Category save = categoryRepository.save(entity);
        return categoryMapper.toDto(save);
    }


    @Transactional
    public void deleteCategoryById(Long id) {
       Optional<Category> category = categoryRepository.findById(id);

       if (category.isPresent()){
           if (!category.get().getPosts().isEmpty()){
               throw new IllegalStateException("Category has posts associated with it");
           }
           categoryRepository.deleteById(id);
       }
   }


    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new EntityNotFoundException("category not found with id" + categoryId));
    }
}
