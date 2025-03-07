package org.selebros.spring_mvc_demo.dao.implementations;

import lombok.RequiredArgsConstructor;
import org.selebros.spring_mvc_demo.dao.interfaces.ProfessionsService;
import org.selebros.spring_mvc_demo.model.entities.Profession;
import org.selebros.spring_mvc_demo.model.repositories.ProfessionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessionServiceImplementation implements ProfessionsService {
    private final ProfessionsRepository repo;

    public List<Profession> all() {
        return repo.findAll();
    }

    public Optional<Profession> findById(int id) {
        return repo.findById(id);
    }

    public Profession save(Profession profession) {
        return repo.save(profession);
    }

    public Profession update(Profession profession) {
        Profession professionToUpdate = repo.findById(profession.getId()).get();
        professionToUpdate.setName(profession.getName());
        professionToUpdate.setNote(profession.getNote());
        return repo.save(professionToUpdate);
    }







    public boolean deleteById(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}

