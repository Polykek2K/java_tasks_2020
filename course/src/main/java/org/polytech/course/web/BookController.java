package org.polytech.course.web;

import org.polytech.course.entity.Book;
import org.polytech.course.exception.BookNotFoundException;
import org.polytech.course.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("books/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> list = bookService.listBooks();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(bookService.findBook(id), HttpStatus.OK);
        } catch (BookNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found!");
        }
    }

    @PostMapping(value = "books/add", consumes = "application/json", produces = "application/json")
    public Book addBook(@RequestBody Book newBook) {
        return bookService.addBook(newBook);
    }


    @PutMapping(value = "books/{id}", consumes = "application/json", produces = "application/json")
    public Book updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        try {
            return bookService.updateBook(id, book);
        } catch (BookNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found!");
        }
    }

    @DeleteMapping("books/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        try {
            bookService.deleteBook(id);
        } catch (BookNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found!");
        }
    }
}
