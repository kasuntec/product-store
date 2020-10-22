package com.kasun.assessment.priceengine.dto;

import lombok.Data;

@Data
public class ShoppingCartDto {
    private Integer id;
    private String description;
    private Integer itemId;
    private String unitType;
    private Integer qty;
    private Double unitPrice;
}
