package com.ayush.postify.domain.dtos;

import com.ayush.postify.enums.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private AuthorDto authorDto;
    private CategoryDto categoryDto;
    private Set<TagResponseDto> tags;
    private Integer readingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private PostStatus postStatus;
}
