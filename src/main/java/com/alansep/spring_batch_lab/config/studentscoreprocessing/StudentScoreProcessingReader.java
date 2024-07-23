package com.alansep.spring_batch_lab.config.studentscoreprocessing;

import com.alansep.spring_batch_lab.model.entity.Student;
import com.alansep.spring_batch_lab.model.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentScoreProcessingReader implements ItemReader<Student> {

    private final StudentRepository studentRepository;

    @Override
    public Student read() {
        final var foundStudent = studentRepository.findFirstByOrderByStudentIdAsc();
        foundStudent.ifPresent(student -> studentRepository.deleteById(student.getId()));
        return foundStudent.orElse(null);
    }
}
