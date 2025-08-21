package com.ayush.postify.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

    private Long id;
    private String name;
    private Long postCount;

}
