package com.alansep.spring_batch_lab.model.repository;

import com.alansep.spring_batch_lab.model.entity.ApprovedStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovedStudentRepository extends JpaRepository<ApprovedStudent, Long> {

}
