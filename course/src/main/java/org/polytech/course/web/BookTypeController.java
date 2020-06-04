package org.polytech.course.web;

import org.polytech.course.entity.BookType;
import org.polytech.course.exception.BookTypeNotFoundException;
import org.polytech.course.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api")
public class BookTypeController {
    @Autowired
    private BookTypeService bookTypeService;

    @GetMapping("types/all")
    public ResponseEntity<List<BookType>> getAllBookTypes() {
        List<BookType> list = bookTypeService.listBookTypes();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("types/{id}")
    public ResponseEntity<BookType> getBookType(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(bookTypeService.findBookType(id), HttpStatus.OK);
        } catch (BookTypeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BookType not found!");
        }
    }

    @PostMapping(value = "types/add", consumes ="application/json", produces = "application/json")
    public BookType addBookType(@RequestBody BookType newBookType) {
        return bookTypeService.addBookType(newBookType);
    }

    @PutMapping(value = "types/{id}", consumes = "application/json", produces = "application/json")
    public BookType updateBookType(@PathVariable("id") long id, @RequestBody BookType bookType) {
        try {
            return bookTypeService.updateBookType(id, bookType);
        } catch (BookTypeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BookType not found!");
        }
    }

    @DeleteMapping("types/{id}")
    public void deleteBookType(@PathVariable("id") long id) {
        try {
            bookTypeService.deleteBookType(id);
        } catch (BookTypeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BookType not found!");
        }
    }
}
