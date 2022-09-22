package com.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column( name = "description")
    @JsonProperty("description")
    private String description;

    @Column( name = "unit_measurement")
    @JsonProperty("unit_measurement")
    private String unitMeasurement;
    
    @Column( name = "date_creation")
    @JsonProperty("date_creation")
    private Date dateCreation;

    @Column( name = "date_updated")
    @JsonProperty("date_updated")
    private Date dateUpdated;

    @Column( name = "person_creation_id", nullable = false)
    @JsonProperty("person_creation_id")
    private Long creationPersonId;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", unit_measurement='" + unitMeasurement + '\'' +
                ", date_creation='" + dateCreation + '\'' +
                ", date_updated='" + dateUpdated + '\'' +
                ", person_creation_id='" + creationPersonId + '\'' +
                '}';
    }
}
