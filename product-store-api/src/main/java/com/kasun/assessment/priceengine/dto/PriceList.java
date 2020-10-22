package com.kasun.assessment.priceengine.dto;

import lombok.Data;

@Data
public class PriceList {
    private Double unitPrice;
    private Double actualPrice;
    private Double netAmount;
    private Integer qty;
}
