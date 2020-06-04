package org.polytech.course.service;

import org.polytech.course.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> listBooks();

    Book findBook(long id);
    Book addBook(Book book);
    Book updateBook(long id, Book book);
    void deleteBook(long id);
}
