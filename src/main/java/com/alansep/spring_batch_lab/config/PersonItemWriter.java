package com.alansep.spring_batch_lab.config;

import com.alansep.spring_batch_lab.model.dto.PersonDTO;
import com.alansep.spring_batch_lab.model.entity.Person;
import com.alansep.spring_batch_lab.model.repository.PersonRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonItemWriter implements ItemWriter<PersonDTO> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void write(Chunk<? extends PersonDTO> chunk) throws Exception {
        chunk.getItems().stream().peek(System.out::println).map(Person::fromPerson).forEach(this::savePerson);
    }

    private void savePerson(Person personDAO) {
        final var result = personRepository.findByFirstNameAndLastName(personDAO.getFirstName(), personDAO.getLastName());
        if (result.isEmpty()) {
            System.out.println("Saving person: " + personDAO);
            personRepository.save(personDAO);
        } else {
            throw new IllegalArgumentException("Person already exists: " + personDAO);
        }
    }
}
