package com.bookstore.api.controller;

import com.bookstore.api.model.Book;
import com.bookstore.api.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 1. CREATE Book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    // 2. READ All Books (Includes dynamic Filter, Pagination, and Sorting)
    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Book> booksPage;
        if (title != null && !title.trim().isEmpty()) {
            booksPage = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else {
            booksPage = bookRepository.findAll(pageable);
        }

        return ResponseEntity.ok(booksPage);
    }

    // 3. READ Single Book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. UPDATE Book by ID
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(bookDetails.getTitle());
            existingBook.setIsbn(bookDetails.getIsbn());
            existingBook.setPrice(bookDetails.getPrice());
            if (bookDetails.getAuthor() != null) {
                existingBook.setAuthor(bookDetails.getAuthor());
            }
            return ResponseEntity.ok(bookRepository.save(existingBook));
        }).orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE Book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

