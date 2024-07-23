package com.alansep.spring_batch_lab.model.entity;

import com.alansep.spring_batch_lab.model.dto.StudentScore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_score")
    private BigDecimal firstScore;

    @Column(name = "second_score")
    private BigDecimal secondScore;

    @Column(name = "third_score")
    private BigDecimal thirdScore;

    @Column(name = "fourth_score")
    private BigDecimal fourthScore;

    public static Student fromStudent(StudentScore person) {
        Student studentDAO = new Student();
        studentDAO.setStudentId(person.id());
        studentDAO.setFirstName(person.firstName());
        studentDAO.setLastName(person.lastName());
        studentDAO.setFirstScore(person.firstScore());
        studentDAO.setSecondScore(person.secondScore());
        studentDAO.setThirdScore(person.thirdScore());
        studentDAO.setFourthScore(person.fourthScore());
        return studentDAO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public BigDecimal getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(BigDecimal firstScore) {
        this.firstScore = firstScore;
    }

    public BigDecimal getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(BigDecimal secondScore) {
        this.secondScore = secondScore;
    }

    public BigDecimal getThirdScore() {
        return thirdScore;
    }

    public void setThirdScore(BigDecimal thirdScore) {
        this.thirdScore = thirdScore;
    }

    public BigDecimal getFourthScore() {
        return fourthScore;
    }

    public void setFourthScore(BigDecimal fourthScore) {
        this.fourthScore = fourthScore;
    }
}
