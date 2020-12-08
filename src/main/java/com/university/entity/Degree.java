package com.university.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="degree")
public class Degree {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    public Degree() {
    }

    public Degree(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Degree degree = (Degree) o;
        return id.equals(degree.id) &&
                name.equals(degree.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
