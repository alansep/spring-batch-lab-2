package com.alansep.spring_batch_lab.config.studentlistreading;

import com.alansep.spring_batch_lab.model.dto.StudentScore;
import com.alansep.spring_batch_lab.model.entity.Student;
import com.alansep.spring_batch_lab.model.repository.StudentRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentItemWriter implements ItemWriter<StudentScore> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void write(Chunk<? extends StudentScore> chunk) throws Exception {
        chunk.getItems().stream().peek(System.out::println).map(Student::fromStudent).forEach(this::savePerson);
    }

    private void savePerson(Student studentDAO) {
        final var result = studentRepository.findByFirstNameAndLastName(studentDAO.getFirstName(), studentDAO.getLastName());
        if (result.isEmpty()) {
            System.out.println("Saving student: " + studentDAO);
            studentRepository.save(studentDAO);
        } else {
            throw new IllegalArgumentException("Student already exists: " + studentDAO);
        }
    }
}
