package com.ayush.postify.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequestDto {

    @NotBlank(message = "Category name is required")
    @Size(min = 2 , max = 20 , message = "Category name must be between {min} and {max} characters")
    @Pattern(regexp = "^[\\w\\s-]+$" , message = "Category name can only contains letters , numbers , spaces and hyphens")
    private String name;
}
