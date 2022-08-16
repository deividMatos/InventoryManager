package com.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PERSON")
@ApiModel(description = "")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    @ApiModelProperty(required = true)
    @JsonProperty("id")
    private Long id;

    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    @ApiModelProperty(required = true, value = "")
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "BIRTH")
    @ApiModelProperty(required = true, value = "1999-01-01")
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