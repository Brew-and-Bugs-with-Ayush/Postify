package com.ayush.postify.service;

import com.ayush.postify.domain.dtos.CreateTagRequestDto;
import com.ayush.postify.domain.dtos.TagResponseDto;
import com.ayush.postify.domain.entity.Tag;
import com.ayush.postify.mappers.TagMapper;
import com.ayush.postify.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;


    public List<TagResponseDto> getAllTags() {
        return tagRepository.findAllByWithPostCount()
                .stream()
                .map(tagMapper::toDto)
                .toList();
    }

    @Transactional
    public List<Tag> createTags(Set<String> tagNames) {
        if (tagNames == null || tagNames.isEmpty()) {
            return Collections.emptyList(); // or handle as per your logic
        }

        List<Tag> existingTags = tagRepository.findByNameIn(tagNames);

        Set<String> existingTagNames = existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .toList();

        List<Tag> savedTags = new ArrayList<>();
        if (!newTags.isEmpty()) {
            savedTags = tagRepository.saveAll(newTags);
        }

        savedTags.addAll(existingTags);

        return savedTags;
    }

    @Transactional
    public void deleteTag(Long id) {
        Optional<Tag> tagById = tagRepository.findById(id);

        if (tagById.isPresent()){
            if (!tagById.get().getPosts().isEmpty()){
                throw new IllegalStateException("Cannot delete tag with posts");
            }
            tagRepository.deleteById(id);
        }
    }

    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() ->
                        new EntityNotFoundException("tag with the id not found" + tagId));
    }

    public List<Tag> getTagByIds(Set<Long> id) {
        List<Tag> foundTags = tagRepository.findAllById(id);
        if(foundTags.size() != id.size()) {
            throw new EntityNotFoundException("Not all specified tag IDs exist");
        }
        return foundTags;
    }
}
