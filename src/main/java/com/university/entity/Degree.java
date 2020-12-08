package com.university.entity;

import javax.persistence.*;

@Entity
@Table(name="degree")
public class Degree {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

}
