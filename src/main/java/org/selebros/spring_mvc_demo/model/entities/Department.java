package org.selebros.spring_mvc_demo.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department_t")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "note")
    private String note;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Department parentDpt;
    @OneToMany(mappedBy = "parentDpt")
    private Set<Department> subDepartments = new HashSet<>();


    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", parentDpt=" + (parentDpt == null ? "null" : String.valueOf(parentDpt.getId())) +
                ", subDepartments=" + subDepartments.stream()
                                                    .map(Department::getId)
                                                    .collect(Collectors.toSet()) +
                '}';
    }
}
