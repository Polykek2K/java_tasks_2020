package org.polytech.course.entity;

import javax.persistence.*;

@Entity
public class BookType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cnt")
    private Integer count;

    @Column(name = "fine")
    private Integer fine;

    @Column(name = "day_count")
    private Integer dayCount;

    public BookType(String name, Integer count, Integer fine, Integer dayCount) {
        this.name = name;
        this.count = count;
        this.fine = fine;
        this.dayCount = dayCount;
    }

    public BookType() {
    }
}