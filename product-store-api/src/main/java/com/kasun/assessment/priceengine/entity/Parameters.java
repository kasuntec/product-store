package com.kasun.assessment.priceengine.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name = "parameters" )
@Data
public class Parameters {

    @Id
    private String keyCode;
    private Double value;

}
