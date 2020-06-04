package org.polytech.course.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "date_beg")
    private Date dateBegin;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "date_ret")
    private Date dateReturn;

    public Journal(Book book, Client client, Date dateBegin, Date dateEnd, Date dateReturn) {
        this.book = book;
        this.client = client;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.dateReturn = dateReturn;
    }

    public Journal() {
    }
}