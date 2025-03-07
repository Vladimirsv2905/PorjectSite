package org.selebros.spring_mvc_demo.model.repositories;

import org.selebros.spring_mvc_demo.model.entities.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionsRepository extends JpaRepository<Profession, Integer> {
}
