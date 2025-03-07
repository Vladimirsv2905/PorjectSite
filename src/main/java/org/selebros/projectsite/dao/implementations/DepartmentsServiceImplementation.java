package org.selebros.projectsite.dao.implementations;

import lombok.RequiredArgsConstructor;
import org.selebros.projectsite.dao.interfaces.DepartmentsService;
import org.selebros.projectsite.model.entities.Department;
import org.selebros.projectsite.model.repositories.DepartmentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentsServiceImplementation implements DepartmentsService {
    private final DepartmentsRepository repo;

    @Override
    public List<Department> all() {
        return repo.findAll();
    }

    @Override
    public Optional<Department> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Department save(Department department) {
        if (department.getParentDpt() != null) {
            Department parent = repo.findById(department.getParentDpt().getId()).get();
            department = repo.save(department);
            parent.getSubDepartments().add(department);
            repo.save(parent);
            return department;
        }
        return repo.save(department);
    }

    @Override
    public Department update(Department department) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
