package org.selebros.spring_mvc_demo.model.repositories;

import org.selebros.spring_mvc_demo.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<Department, Integer> {
}
