package com.ayush.postify.controller;

import com.ayush.postify.domain.dtos.CreateTagRequestDto;
import com.ayush.postify.domain.dtos.TagResponseDto;
import com.ayush.postify.domain.entity.Tag;
import com.ayush.postify.mappers.TagMapper;
import com.ayush.postify.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponseDto>> getAllTags(){
        List<TagResponseDto> allTags = tagService.getAllTags();
        return new ResponseEntity<>(allTags , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<TagResponseDto>> createTags(@RequestBody CreateTagRequestDto createTagRequestDto) {
        List<Tag> savedTags = tagService.createTags(createTagRequestDto.getNames());
        List<TagResponseDto> createdTagResponses = savedTags.stream().map(tagMapper::toDto).toList();
        return new ResponseEntity<>(
                createdTagResponses,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTags(@PathVariable Long id){
        tagService.deleteTag(id);
        return new ResponseEntity<>(true , HttpStatus.NO_CONTENT);
    }
}
