package com.alansep.spring_batch_lab.model.entity;

import com.alansep.spring_batch_lab.model.dto.ScoreCalcResultDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "reproved_student")
public class ReprovedStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "final_score")
    private BigDecimal finalScore;


    public static ReprovedStudent fromStudent(Student student, ScoreCalcResultDTO scoreCalcResultDTO){
        ReprovedStudent approvedStudent = new ReprovedStudent();
        approvedStudent.setStudentId(student.getStudentId());
        approvedStudent.setFirstName(student.getFirstName());
        approvedStudent.setLastName(student.getLastName());
        approvedStudent.setFinalScore(scoreCalcResultDTO.average());
        return approvedStudent;
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

    public BigDecimal getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(BigDecimal finalScore) {
        this.finalScore = finalScore;
    }

}
