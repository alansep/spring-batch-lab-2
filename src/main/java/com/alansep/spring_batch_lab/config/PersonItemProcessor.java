package com.alansep.spring_batch_lab.config;

import com.alansep.spring_batch_lab.model.dto.PersonDTO;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<PersonDTO, PersonDTO> {

    @Override
    public PersonDTO process(PersonDTO person) throws Exception {
        final String firstName = person.firstName().toUpperCase();
        final String lastName = person.lastName().toUpperCase();

        final PersonDTO transformedPerson = new PersonDTO(firstName, lastName);

        System.out.println("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}
