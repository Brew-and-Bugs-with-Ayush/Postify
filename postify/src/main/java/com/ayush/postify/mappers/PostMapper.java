package com.ayush.postify.mappers;

import com.ayush.postify.domain.dtos.*;
import com.ayush.postify.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "authorDto" , source = "author")
    @Mapping(target = "categoryDto" , source = "category")
    @Mapping(target = "tags" , source = "tags")
    @Mapping(target = "postStatus", source = "postStatus")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);
}
