package com.ayush.postify.domain.dtos;

import com.ayush.postify.enums.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostRequestDto {
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 200, message = "Title must be between {min} and {max} characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 10, max = 50000, message = "Content must be between {min} and {max} characters")
    private String content;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @Builder.Default
    @Size(max = 10, message = "Maximum {max} tags allowed")
    private Set<Long> tagIds = new HashSet<>();

    @NotNull(message = "Status is required")
    private PostStatus status;
}
