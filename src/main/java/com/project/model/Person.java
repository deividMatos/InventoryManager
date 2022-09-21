package com.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    @JsonProperty("id")
    private Long id;

    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "BIRTH")
    @JsonProperty("birth")
    private Date birth;


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}