package com.example.hibernatecoba.controller;

import com.example.hibernatecoba.common.CommonUtils;
import com.example.hibernatecoba.dto.request.PostCreateRequest;
import com.example.hibernatecoba.dto.response.PostReport;
import com.example.hibernatecoba.dto.response.PostReport2;
import com.example.hibernatecoba.repository.PostRepository;
import com.example.hibernatecoba.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @GetMapping
    public Page<PostReport2> getAll(Pageable pageable,
                                    @RequestParam(required = false) Long id,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) String description,
                                    @RequestParam(required = false) String content,
                                    @RequestParam(required = false) String authorName
    ) {

        Pageable p = CommonUtils.pageableAllowedSort(pageable, PostReport2.allFields);
        return postService.getAllWithFilter(id, title, description, content, authorName, p);
    }

    @GetMapping("/{id}")
    public Optional<PostReport> getOne(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody PostCreateRequest request) {
        postService.storePost(request);
        return ResponseEntity.ok().build();
    }

}
