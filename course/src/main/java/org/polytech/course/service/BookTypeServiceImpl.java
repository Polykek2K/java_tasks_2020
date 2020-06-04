package org.polytech.course.service;

import org.polytech.course.entity.BookType;
import org.polytech.course.exception.BookTypeNotFoundException;
import org.polytech.course.repository.BookTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Override
    public List<BookType> listBookTypes() {
        return (List<BookType>) bookTypeRepository.findAll();
    }

    @Override
    public BookType findBookType(long id) {
        Optional<BookType> optionalBookType = bookTypeRepository.findById(id);
        if (optionalBookType.isPresent()) {
            return optionalBookType.get();
        } else {
            throw new BookTypeNotFoundException("Book not found!");
        }
    }

    @Override
    public BookType addBookType(BookType bookType) {
        return bookTypeRepository.save(bookType);
    }

    @Override
    public BookType updateBookType(long id, BookType bookType) {
        BookType existingBookType = findBookType(id);
        BeanUtils.copyProperties(bookType, existingBookType);
        return bookTypeRepository.save(existingBookType);
    }

    @Override
    public void deleteBookType(long id) {
        bookTypeRepository.delete(findBookType(id));
    }
}
