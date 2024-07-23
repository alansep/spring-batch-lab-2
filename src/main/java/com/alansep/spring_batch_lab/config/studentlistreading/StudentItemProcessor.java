package com.alansep.spring_batch_lab.config.studentlistreading;

import com.alansep.spring_batch_lab.model.dto.StudentScore;
import org.springframework.batch.item.ItemProcessor;

public class StudentItemProcessor implements ItemProcessor<StudentScore, StudentScore> {

    @Override
    public StudentScore process(StudentScore person) throws Exception {
        final String firstName = person.firstName().toUpperCase();
        final String lastName = person.lastName().toUpperCase();

        final StudentScore transformedPerson = new StudentScore(person.id(), firstName, lastName, person.firstScore(), person.secondScore(), person.thirdScore(), person.fourthScore());

        System.out.println("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}
