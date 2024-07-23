package com.alansep.spring_batch_lab.model.repository;

import com.alansep.spring_batch_lab.model.entity.ReprovedStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReprovedStudentRepository extends JpaRepository<ReprovedStudent, Long> {

}
