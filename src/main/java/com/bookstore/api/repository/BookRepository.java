package com.bookstore.api.repository;

import com.bookstore.api.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Search query for case-insensitive filtering by book title
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
