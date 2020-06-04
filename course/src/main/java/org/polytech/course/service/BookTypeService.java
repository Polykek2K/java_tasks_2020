package org.polytech.course.service;

import org.polytech.course.entity.BookType;

import java.util.List;

public interface BookTypeService {
    List<BookType> listBookTypes();

    BookType findBookType(long id);
    BookType addBookType(BookType bookType);
    BookType updateBookType(long id, BookType bookType);
    void deleteBookType(long id);
}
