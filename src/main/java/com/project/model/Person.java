package com.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "PERSON")
@Data
public class Person {

    @Id
    @GeneratedValue(generator= "generateId", strategy = GenerationType.SEQUENCE) //gerar id sequencial automatico
    @SequenceGenerator(name="generateId",sequenceName = "personSequence")
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