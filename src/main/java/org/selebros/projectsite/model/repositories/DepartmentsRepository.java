package org.selebros.projectsite.model.repositories;

import org.selebros.projectsite.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<Department, Integer> {
}
