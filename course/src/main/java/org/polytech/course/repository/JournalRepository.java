package org.polytech.course.repository;

import org.polytech.course.entity.Journal;
import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<Journal, Long> {
}

