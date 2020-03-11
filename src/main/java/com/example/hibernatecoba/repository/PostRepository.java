package com.example.hibernatecoba.repository;

import com.example.hibernatecoba.dto.response.PostReport;
import com.example.hibernatecoba.entity.Post;
import com.example.hibernatecoba.repository.custom.CustomPostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository {

    @QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    @Query("select " +
            "p.id as id," +
            "p.title as title," +
            "p.description as description," +
            "p.content as content," +
            "p.date as date," +
            "concat(a.firstName, a.lastName) as authorName" +
            " from Post p left join p.author a where p.id=?1")
    List<PostReport> findReportUsingId(Long id, Pageable pageable);
}
