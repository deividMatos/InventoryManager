package com.project.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "productid")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_measurement")
    private String unitMeasurement;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_updated")
    private Date dateUpdated;

    @Column(name = "creation_person_id", nullable = false)
    private Long creationPersonId;

}
