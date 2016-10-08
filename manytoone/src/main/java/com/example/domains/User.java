package com.example.domains;

import lombok.Data;

import javax.persistence.*;

/**
 * User Entity
 * Created by bequs-xhjlee on 2016-10-08.
 */
@Entity
@Table(name = "TBL_USER")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long no;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Dept dept;

    public User(String name) {
        this.name = name;
    }

    public User(String name, Dept dept) {
        this.name = name;
        this.dept = dept;
    }
}
