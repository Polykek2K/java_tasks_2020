package org.polytech.course.web;

import org.polytech.course.entity.Journal;
import org.polytech.course.exception.JournalNotFoundException;
import org.polytech.course.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api")
public class JournalController {
    @Autowired
    private JournalService journalService;

    @GetMapping("journals/all")
    public ResponseEntity<List<Journal>> getAllJournals() {
        List<Journal> list = journalService.listJournals();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("journals/{id}")
    public ResponseEntity<Journal> getJournal(@PathVariable("id") long id) throws ResponseStatusException {
        try {
            return new ResponseEntity<>(journalService.findJournal(id), HttpStatus.OK);
        } catch (JournalNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal not found!");
        }
    }

    @PostMapping(value = "journals/add", consumes = "application/json", produces = "application/json")
    public Journal addJournal(@RequestBody Journal newJournal) {
        return journalService.addJournal(newJournal);
    }

    @PutMapping(value = "journals/{id}", consumes = "application/json", produces = "application/json")
    public Journal updateJournal(@PathVariable("id") long id, @RequestBody Journal journal) {
        try {
            return journalService.updateJournal(id, journal);
        } catch (JournalNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal not found!");
        }
    }

    @DeleteMapping("journals/{id}")
    public void deleteJournal(@PathVariable("id") long id) {
        try {
            journalService.deleteJournal(id);
        } catch (JournalNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal not found!");
        }
    }
}
