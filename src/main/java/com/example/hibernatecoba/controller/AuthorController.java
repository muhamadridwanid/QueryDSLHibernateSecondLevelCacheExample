package com.example.hibernatecoba.controller;

import com.example.hibernatecoba.dto.request.AuthorCreateRequest;
import com.example.hibernatecoba.dto.response.AuthorReport;
import com.example.hibernatecoba.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    private Page<AuthorReport> getAuthor(Pageable pageable) {
        return authorService.getAllAuthor(pageable);
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody AuthorCreateRequest request) {
        authorService.storeAuthor(request);
        return ResponseEntity.ok().build();
    }

}
