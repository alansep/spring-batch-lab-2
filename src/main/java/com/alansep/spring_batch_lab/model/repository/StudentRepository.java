package com.alansep.spring_batch_lab.model.repository;

import com.alansep.spring_batch_lab.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<Student> findFirstByOrderByStudentIdAsc();
}
