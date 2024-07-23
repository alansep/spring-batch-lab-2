package com.alansep.spring_batch_lab.config.studentscoreprocessing;

import com.alansep.spring_batch_lab.model.entity.ApprovedStudent;
import com.alansep.spring_batch_lab.model.entity.ReprovedStudent;
import com.alansep.spring_batch_lab.model.entity.Student;
import com.alansep.spring_batch_lab.model.repository.ApprovedStudentRepository;
import com.alansep.spring_batch_lab.model.repository.ReprovedStudentRepository;
import com.alansep.spring_batch_lab.model.repository.StudentRepository;
import com.alansep.spring_batch_lab.services.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class StudentScoreProcessingWriter implements ItemWriter<Student> {

    private final ApprovedStudentRepository approvedStudentRepository;
    private final ReprovedStudentRepository reprovedStudentRepository;
    private final ScoreService scoreService;

    @Override
    public void write(Chunk<? extends Student> chunk) throws Exception {
        chunk.getItems().forEach(student -> {
            final var average = scoreService.calculateAverage(student.getFirstScore(), student.getSecondScore(), student.getThirdScore(), student.getFourthScore());
            if (average.compareTo(new BigDecimal("6.75")) >= 0) {
                final var approvedStudent = new ApprovedStudent();
                approvedStudent.setStudentId(student.getStudentId());
                approvedStudent.setFirstName(student.getFirstName());
                approvedStudent.setLastName(student.getLastName());
                approvedStudent.setFinalScore(average);
                approvedStudent.setApprovedOnScoreRounding(average.compareTo(new BigDecimal("7")) < 0);
                approvedStudentRepository.save(approvedStudent);
            } else {
                final var reprovedStudent = new ReprovedStudent();
                reprovedStudent.setStudentId(student.getStudentId());
                reprovedStudent.setFirstName(student.getFirstName());
                reprovedStudent.setLastName(student.getLastName());
                reprovedStudent.setFinalScore(average);
                reprovedStudentRepository.save(reprovedStudent);
            }
        });
    }
}
