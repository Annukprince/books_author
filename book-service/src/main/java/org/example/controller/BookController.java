package org.example.controller;

import org.example.dto.BookDto;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.example.client.AuthorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorClient authorClient;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    private BookDto convertToDto(Book book) {
        var author = authorClient.getAuthorById(book.getAuthorId());
        return new BookDto(book.getId(), book.getTitle(), author);
    }
}
