package org.polytech.course.entity;

import javax.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pather_name")
    private String patherName;

    @Column(name = "passport_seria")
    private String passportSeria;

    @Column(name = "passport_num")
    private String passportNum;

    public Client(String firstName, String lastName, String patherName, String passportSeria, String passportNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.passportSeria = passportSeria;
        this.passportNum = passportNum;
    }

    public Client() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatherName() {
        return patherName;
    }

    public void setPatherName(String patherName) {
        this.patherName = patherName;
    }

    public String getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }
}
