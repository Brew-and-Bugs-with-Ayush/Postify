package com.ayush.postify.mappers;

import com.ayush.postify.domain.dtos.CategoryDto;
import com.ayush.postify.domain.dtos.CreateCategoryRequestDto;
import com.ayush.postify.domain.entity.Category;
import com.ayush.postify.domain.entity.Post;
import com.ayush.postify.enums.PostStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount" , source = "posts" , qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequestDto createCategoryRequestDto);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if (null == posts){
            return 0;
        }
        return posts.stream()
                .filter(post ->
                        PostStatus.PUBLISHED.equals(post.getPostStatus()))
                .count();
    }
}
