package com.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "movement_type")
@Data
@Builder
@AllArgsConstructor
public class MovementType {
    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column( name = "description", nullable = false)
    @JsonProperty("description")
    private String description;

    @Column( name = "date_creation", nullable = false)
    @JsonProperty("date_creation")
    private Date dateCreation;

    @Column( name = "date_updated")
    @JsonProperty("date_updated")
    private Date dateUpdated;

    @Column( name = "status")
    @JsonProperty("status")
    private Boolean status;

    @Column( name = "person_creation_id", nullable = false)
    @JsonProperty("person_creation_id")
    private Long creationPersonId;

    public MovementType() {
    }

    @Override
    public String toString() {
        return "MovementType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date_creation='" + dateCreation + '\'' +
                ", date_updated='" + dateUpdated + '\'' +
                ", status='" + status + '\'' +
                ", person_creation_id='" + creationPersonId + '\'' +
                '}';
    }
}
