package org.polytech.course.service;

import org.polytech.course.entity.Journal;
import org.polytech.course.exception.JournalNotFoundException;
import org.polytech.course.repository.JournalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    private JournalRepository journalRepository;

    @Override
    public List<Journal> listJournals() {
        return (List<Journal>) journalRepository.findAll();
    }

    @Override
    public Journal findJournal(long id) {
        Optional<Journal> optionalJournal = journalRepository.findById(id);
        if (optionalJournal.isPresent()) {
            return optionalJournal.get();
        } else {
            throw new JournalNotFoundException("Book not found!");
        }
    }

    @Override
    public Journal addJournal(Journal journal) {
        return journalRepository.save(journal);
    }

    @Override
    public Journal updateJournal(long id, Journal journal) {
        Journal existingJournal = findJournal(id);
        BeanUtils.copyProperties(journal, existingJournal);
        return journalRepository.save(existingJournal);
    }

    @Override
    public void deleteJournal(long id) {
        journalRepository.delete(findJournal(id));
    }
}
