package com.example.domains;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

/**
 * Dept Entity
 * Created by bequs-xhjlee on 2016-10-08.
 */
@Entity
@Table(name = "TBL_DEPT")
@Data
@ToString(exclude = {"userCollection"})
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long no;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL)
    private Collection<User> userCollection;

    public Dept(String name) {
        this.name = name;
    }

    public Dept(String name, Collection<User> userCollection) {
        this.name = name;
        this.userCollection = userCollection;
    }
}
