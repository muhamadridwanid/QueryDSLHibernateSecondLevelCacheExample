package com.example.hibernatecoba.service;

import com.example.hibernatecoba.dto.request.PostCreateRequest;
import com.example.hibernatecoba.dto.response.PostReport;
import com.example.hibernatecoba.dto.response.PostReport2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostService {
    void storePost(PostCreateRequest createRequest);

    Optional<PostReport> getPostById(Long id);

    Page<PostReport2> getAllWithFilter(Long id, String title, String description, String content, String authorName, Pageable pageable);

}
