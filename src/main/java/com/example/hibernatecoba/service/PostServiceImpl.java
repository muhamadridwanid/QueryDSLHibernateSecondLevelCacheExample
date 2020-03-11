package com.example.hibernatecoba.service;

import com.example.hibernatecoba.dto.request.PostCreateRequest;
import com.example.hibernatecoba.dto.response.PostReport;
import com.example.hibernatecoba.dto.response.PostReport2;
import com.example.hibernatecoba.entity.Author;
import com.example.hibernatecoba.entity.Post;
import com.example.hibernatecoba.repository.AuthorRepository;
import com.example.hibernatecoba.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public void storePost(PostCreateRequest createRequest) {
        Author author = authorRepository.findById(createRequest.getAuthorId()).orElseThrow(() -> new RuntimeException("invalid author id"));
        Post post = new Post();
        BeanUtils.copyProperties(createRequest, post);
        post.setAuthor(author);
        postRepository.save(post);
    }

    @Override
    public Optional<PostReport> getPostById(Long id) {
        return postRepository.findReportUsingId(id, PageRequest.of(0, 1))
                .stream()
                .findFirst();
    }

    @Override
    public Page<PostReport2> getAllWithFilter(Long id, String title, String description, String content, String authorName, Pageable pageable) {
        return postRepository.findReportAllFilter(pageable, id, title, description, content, authorName);

    }
}
