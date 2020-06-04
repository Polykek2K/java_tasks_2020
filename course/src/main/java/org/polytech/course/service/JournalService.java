package org.polytech.course.service;

import org.polytech.course.entity.Journal;

import java.util.List;

public interface JournalService {
    List<Journal> listJournals();

    Journal findJournal(long id);
    Journal addJournal(Journal journal);
    Journal updateJournal(long id, Journal journal);
    void deleteJournal(long id);
}