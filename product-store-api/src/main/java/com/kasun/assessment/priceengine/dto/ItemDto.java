package com.kasun.assessment.priceengine.dto;

import lombok.Data;

@Data
public class ItemDto {
    private Integer id;
    private String description;
    private Integer unitPerCarton;
    private Double cartonPrice;
    private Double unitPrice;
}
