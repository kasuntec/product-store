package com.kasun.assessment.priceengine.service;

import com.kasun.assessment.priceengine.dto.ItemDto;
import com.kasun.assessment.priceengine.entity.Item;

public interface PriceCalService {


    public Double calculateUnitPrice(ItemDto item, Integer qty) ;



}
