package org.polytech.course.service;

import org.polytech.course.entity.Book;
import org.polytech.course.exception.BookNotFoundException;
import org.polytech.course.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> listBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Book findBook(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new BookNotFoundException("Book not found!");
        }
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(long id, Book book) {
        Book existingBook = findBook(id);
        BeanUtils.copyProperties(book, existingBook);
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.delete(findBook(id));
    }
}
