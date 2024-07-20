package com.alansep.spring_batch_lab.model.entity;

import com.alansep.spring_batch_lab.model.dto.PersonDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public static Person fromPerson(PersonDTO person) {
        Person personDAO = new Person();
        personDAO.setFirstName(person.firstName());
        personDAO.setLastName(person.lastName());
        return personDAO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
