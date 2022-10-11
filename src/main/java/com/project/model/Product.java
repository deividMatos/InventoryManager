package com.project.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(generator= "generateId", strategy = GenerationType.SEQUENCE) //gerar id sequencial automatico
    @SequenceGenerator(name="generateId",sequenceName = "productSequence")
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    private Long id;

    @Column( name = "description")
    @JsonProperty("description")
    private String description;

    @Column( name = "unit_measurement")
    @JsonProperty("unitMeasurement")
    private String unitMeasurement;

    @Column( name = "date_creation")
    @JsonProperty("dateCreation")
    private Date dateCreation;

    @Column( name = "date_updated")
    @JsonProperty("dateUpdated")
    private Date dateUpdated;

    @Column( name = "person_creation_id", nullable = false)
    @JsonProperty("personCreationId")
    private Long creationPersonId;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", unitMeasurement='" + unitMeasurement + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                ", personCreationId='" + creationPersonId + '\'' +
                '}';
    }
}
