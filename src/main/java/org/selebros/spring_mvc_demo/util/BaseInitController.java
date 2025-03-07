package org.selebros.spring_mvc_demo.util;

import lombok.RequiredArgsConstructor;
import org.selebros.spring_mvc_demo.dao.interfaces.DepartmentsService;
import org.selebros.spring_mvc_demo.dao.interfaces.ProfessionsService;
import org.selebros.spring_mvc_demo.model.entities.Department;
import org.selebros.spring_mvc_demo.model.entities.Profession;
import org.selebros.spring_mvc_demo.security.dao.UserRepository;
import org.selebros.spring_mvc_demo.security.entity.Role;
import org.selebros.spring_mvc_demo.security.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;

@Controller
@RequestMapping("/s")
@RequiredArgsConstructor
public class BaseInitController {
    private final DepartmentsService daoDept;
    private final ProfessionsService daoProf;
    private final UserRepository repo;

    @GetMapping("/gen")
    public String generate() {
      departmentsInit();
      profsInit();
        usersInit();
        return "redirect:/index.html";
    }

    private void departmentsInit() {
        Department root1 = daoDept.save(Department.builder().name("DB").note("отдел по разработке DB").parentDpt(null).subDepartments(new HashSet<>()).build());
        Department dev1 = daoDept.save(Department.builder().name("Разработка").note("родитель - 1: DB").parentDpt(root1).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Dev").note("родитель - 2: Разработка").parentDpt(dev1).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Test").note("родитель - 2: Разработка").parentDpt(dev1).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Design").note("родитель - 2: Разработка").parentDpt(dev1).subDepartments(new HashSet<>()).build());
        Department mark1 = daoDept.save(Department.builder().name("Маркетинг").note("родитель - 1: DB").parentDpt(root1).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Колл-центр").note("родитель - 6: Маркетинг").parentDpt(mark1).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Медиа").note("родитель - 6: Маркетинг").parentDpt(mark1).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Бухгалтерия").note("родитель - 1: DB").parentDpt(root1).subDepartments(new HashSet<>()).build());

        Department root2 = daoDept.save(Department.builder().name("Office").note("отдел по разработке Office").subDepartments(new HashSet<>()).parentDpt(null).build());
        Department dev2 = daoDept.save(Department.builder().name("Разработка").note("родитель - 10: Office").subDepartments(new HashSet<>()).parentDpt(root2).build());
        daoDept.save(Department.builder().name("Dev").note("родитель - 10: Разработка").parentDpt(dev2).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Test").note("родитель - 10: Разработка").parentDpt(dev2).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Design").note("родитель - 10: Разработка").parentDpt(dev2).subDepartments(new HashSet<>()).build());
        Department mark2 = daoDept.save(Department.builder().name("Маркетинг").note("родитель - 10: Office").parentDpt(root2).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Колл-центр").note("родитель - 15: Маркетинг").parentDpt(mark2).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Медиа").note("родитель - 15: Маркетинг").parentDpt(mark2).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Соц Сети").note("родитель - 15: Маркетинг").parentDpt(mark2).subDepartments(new HashSet<>()).build());
        daoDept.save(Department.builder().name("Бухгалтерия").note("родитель - 10: Office").parentDpt(root2).subDepartments(new HashSet<>()).build());
        daoDept.all().forEach(System.out::println);
    }

    private void profsInit() {
        daoProf.save(Profession.builder().name("ПМ").note("").build());
        daoProf.save(Profession.builder().name("Лид").note("").build());
        daoProf.save(Profession.builder().name("Разработчик").note("").build());
        daoProf.save(Profession.builder().name("Тестировщик").note("").build());
        daoProf.save(Profession.builder().name("Дизайнер").note("").build());
        daoProf.save(Profession.builder().name("PR-менеджер").note("").build());
        daoProf.save(Profession.builder().name("Специалист колл-центра").note("").build());
        daoProf.save(Profession.builder().name("Маркетолог").note("").build());
        daoProf.save(Profession.builder().name("Бухгалтер").note("").build());
        System.out.println(daoProf.all());
    }

    private void usersInit() {
        repo.save(User.builder()
                .username("admin")
                .password("$2a$12$jxp1fObbhb6oUuv5jvyCg.uTtHH3PxTx69BgjRA2zl04xxCaTBjny")
                .role(Role.ROLE_ADMIN)
                .build());
        repo.save(User.builder()
                .username("user")
                .password("$2a$12$Ok9WDbySalRPeJVuCJU.8ebATKKWMT2SToAb/KbtU0078h0njlJa.")
                .role(Role.ROLE_USER)
                .build());
        repo.save(User.builder()
                .username("loser")
                .password("$2a$12$F/YsGaHjWWN19QjCKUvHDOZKC8V7Wt7gWzVqhnJxi.kL5Mx2XlP.m")
                .role(Role.ROLE_LOSER)
                .build());
    }
}
