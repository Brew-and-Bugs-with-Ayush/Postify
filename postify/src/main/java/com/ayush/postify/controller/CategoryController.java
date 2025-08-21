package com.ayush.postify.controller;

import com.ayush.postify.domain.dtos.CategoryDto;
import com.ayush.postify.domain.dtos.CreateCategoryRequestDto;
import com.ayush.postify.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {

        List<CategoryDto> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @Valid
            @RequestBody CreateCategoryRequestDto createCategoryRequestDto) {
        try {
            CategoryDto category = categoryService.createCategory(createCategoryRequestDto);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error("category not created", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @Valid
            @RequestBody CreateCategoryRequestDto createCategoryRequestDto ,
            @PathVariable Long id){
        try {
            CategoryDto categoryDto = categoryService.updateCategory(createCategoryRequestDto, id);
            return new ResponseEntity<>(categoryDto , HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("updation failed");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
