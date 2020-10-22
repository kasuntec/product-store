package com.kasun.assessment.priceengine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String description;
    private Integer unitPerCarton;
    private Double cartonPrice;
    private Double unitPrice;
}
