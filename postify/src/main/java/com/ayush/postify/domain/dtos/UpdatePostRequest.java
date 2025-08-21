package com.ayush.postify.domain.dtos;

import com.ayush.postify.enums.PostStatus;
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
public class UpdatePostRequest {

    private Long id;

    private String title;

    private String content;

    private Long categoryId;

    @Builder.Default
    private Set<Long> tagIds = new HashSet<>();

    private PostStatus status;
}
