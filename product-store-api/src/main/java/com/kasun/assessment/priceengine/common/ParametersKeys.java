package com.kasun.assessment.priceengine.common;

public enum ParametersKeys {
    SINGLE_UNIT_PRICE_INCREASE_RATE( "single_unit_price_increase_rate"),
    DIS_FOR_ABOVE_3_CARTON_PER( "discount_for_above_3carton_per");

    private String value;

    private ParametersKeys(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
