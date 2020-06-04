package org.polytech.course.entity;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cnt")
    private Integer count;


    @ManyToOne
    @JoinColumn(name = "type_id")
    private BookType bookType;

    public Book(String name, Integer count, BookType type) {
        this.name = name;
        this.count = count;
        this.bookType = type;
    }

    public Book() {
    }
}