package com.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "movement")
@Data
@Builder
public class Movement {
    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column( name = "date_creation", nullable = false)
    @JsonProperty("date_creation")
    private Date dateCreation;

    @Column( name = "quantity", nullable = false)
    @JsonProperty("quantity")
    private Long quantity;

    @Column( name = "product_id", nullable = false)
    @JsonProperty("product_id")
    private Long productId;

    @Column( name = "person_creation_id", nullable = false)
    @JsonProperty("person_creation_id")
    private Long creationPersonId;

    @Column( name = "movement_type_id", nullable = false)
    @JsonProperty("movement_type_id")
    private Long movementTypeId;

    @Override
    public String toString() {
        return "MovementType{" +
                "id=" + id +
                ", date_creation='" + dateCreation + '\'' +
                ", quantity='" + quantity + '\'' +
                ", product_id='" + productId + '\'' +
                ", person_creation_id='" + creationPersonId + '\'' +
                ", movement_type_id='" + movementTypeId + '\'' +
                '}';
    }
}