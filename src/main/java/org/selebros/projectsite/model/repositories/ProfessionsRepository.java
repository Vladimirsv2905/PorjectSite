package org.selebros.projectsite.model.repositories;

import org.selebros.projectsite.model.entities.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionsRepository extends JpaRepository<Profession, Integer> {
}
