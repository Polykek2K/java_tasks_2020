package org.polytech.course.repository;

import org.polytech.course.entity.BookType;
import org.springframework.data.repository.CrudRepository;

public interface BookTypeRepository extends CrudRepository<BookType, Long> {
}