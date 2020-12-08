package com.university.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="lector")
public class Lector {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Digits(integer=10, fraction=2)
    @Column(name="salary")
    @NotNull
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Degree degree;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="lector_department",
            joinColumns=@JoinColumn(name="lector_id"),
            inverseJoinColumns=@JoinColumn(name="department_id")
    )
    private List<Department> departments;

    public Lector() {
    }

    public Lector(Long id, @NotNull String name, @Digits(integer = 10, fraction = 2)
    @NotNull BigDecimal salary, Degree degree, List<Department> departments) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.degree = degree;
        this.departments = departments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
