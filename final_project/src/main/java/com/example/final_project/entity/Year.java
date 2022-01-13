package com.example.final_project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "year")
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;



    public Year() {
    }

    public Year(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
