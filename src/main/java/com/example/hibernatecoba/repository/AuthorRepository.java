package com.example.hibernatecoba.repository;

import com.example.hibernatecoba.dto.response.AuthorReport;
import com.example.hibernatecoba.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @QueryHints(
            value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true")
    )
    @Query("select " +
            "a.firstName as firstName, " +
            "a.lastName as lastName " +
            "from Author a")
    Page<AuthorReport> findAuthorReports(Pageable pageable);
}
