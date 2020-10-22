package com.kasun.assessment.priceengine.service;

import com.kasun.assessment.priceengine.dto.PriceList;
import com.kasun.assessment.priceengine.dto.ItemDto;
import com.kasun.assessment.priceengine.dto.ShoppingCartDto;

import java.util.HashMap;
import java.util.List;

public interface ItemService {

    public ItemDto findById(Integer id);

    public List<PriceList> listAllPriceByItem(Integer id, Integer fromQty, Integer toQty);

    public HashMap calculateActualPrice(Integer id, String unitType, Integer qty);

    public List<ItemDto> findAll();

    public ShoppingCartDto calculateShoppingCart(ShoppingCartDto shoppingCartDto);



}
