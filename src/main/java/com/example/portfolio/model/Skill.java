package com.example.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category; //E.g. backend, frontend, database
    private Integer proficiency; //familiar rate (1 - 100)

    public Skill(String name, String category, int proficiency) {
        this.name = name;
        this.category = category;
        this.proficiency = proficiency;
    }

    public Skill(){

    }

    // Getter and Setter method

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getProficiency() {
        return proficiency;
    }

    public void setProficiency(Integer proficiency) {
        this.proficiency = proficiency;
    }
}
