package com.kasun.assessment.priceengine.service.impl;

import com.kasun.assessment.priceengine.dto.PriceList;
import com.kasun.assessment.priceengine.dto.ItemDto;
import com.kasun.assessment.priceengine.dto.ShoppingCartDto;
import com.kasun.assessment.priceengine.entity.Item;
import com.kasun.assessment.priceengine.repository.ItemRepository;
import com.kasun.assessment.priceengine.service.ItemService;
import com.kasun.assessment.priceengine.service.PriceCalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PriceCalService priceCalService;

    private ModelMapper modelMapper;

    /**
     * service implementation for find item by id
     * @param id
     * @return
     */
    @Override
    public ItemDto findById(Integer id) {

        if(id!=null) {
            // get item from DB
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Item not found"));

            modelMapper =new ModelMapper();
            return modelMapper.map(item, ItemDto.class);
        }
        else {
            throw new EntityNotFoundException("Item not found");
        }

    }

    /**
     * service implementation for return price list for give item and qty range (from qty to to qty)
     * @param id
     * @param fromQty
     * @param toQty
     * @return
     */
    @Override
    public List<PriceList> listAllPriceByItem(Integer id, Integer fromQty, Integer toQty) {
        List<PriceList> displayItems =new ArrayList<>();

        // get item from DB
        ItemDto item = findById(id);

        // loop  fromQty to toQty
        for (int i = fromQty; i <= toQty; i++) {

            //crete new item
            PriceList priceList =new PriceList();
            priceList.setQty(i);

            // get actual price from price engine
            Double actualPrice = priceCalService.calculateUnitPrice(item, i);
            priceList.setActualPrice(actualPrice);
            priceList.setNetAmount(actualPrice*i);
            priceList.setUnitPrice(item.getUnitPrice());

            //add item to list
            displayItems.add(priceList);

        }


        return displayItems;
    }

    /**
     * service implementation for get actual price for given item and qty
     * @param id
     * @param unitType
     * @param qty
     * @return
     */
    @Override
    public HashMap calculateActualPrice(Integer id, String unitType, Integer qty) {

        HashMap values = new HashMap();

        Double actualPrice = 0.00;
        Integer totalQty = 0;
        // get item from DB
        ItemDto item = findById(id);

        // check unit type carton ot single unit
        switch (unitType) {

            case "Carton":
                // get actual price from price engine
                actualPrice = priceCalService.calculateUnitPrice(item,
                        qty*item.getUnitPerCarton());
                totalQty = qty*item.getUnitPerCarton();
                break;

            case "Single":
                actualPrice = priceCalService.calculateUnitPrice(item, qty);
                totalQty = qty;
                break;

            default:
                break;

        }


        //set total qty and actual unit price to map
        values.put("totalQty", totalQty);
        values.put("actualPrice", actualPrice);

        return values;
    }


    /**
     * Service implementation for find all items
     * @return List<ItemDto>
     */
    @Override
    public List<ItemDto> findAll() {
        modelMapper = new ModelMapper();
        List<ItemDto> itemDtos =new ArrayList<>();
        itemRepository.findAll().forEach(obj-> {
            ItemDto itemDto = modelMapper.map(obj, ItemDto.class);
            itemDtos.add(itemDto);

        });

        return itemDtos;
    }

    /**
     * service implementation for calculate shopping cart
     * @param shoppingCartDto
     * @return
     */
    @Override
    public ShoppingCartDto calculateShoppingCart(ShoppingCartDto shoppingCartDto) {
        ShoppingCartDto procced =new ShoppingCartDto();

        // get item from DB
        ItemDto item = findById(shoppingCartDto.getItemId());

        Double actualPrice = 0.0;

        // check unit type carton ot single unit
        switch (shoppingCartDto.getUnitType()) {

            case "Carton":
                // get actual price from price engine
                actualPrice = priceCalService.calculateUnitPrice(item,
                        shoppingCartDto.getQty()*item.getUnitPerCarton());
                break;

            case "Single":
                actualPrice = priceCalService.calculateUnitPrice(item, shoppingCartDto.getQty());
                break;

            default:
                break;

        }

        procced.setItemId(item.getId());
        procced.setDescription(item.getDescription());
        procced.setUnitType(shoppingCartDto.getUnitType());
        procced.setQty(shoppingCartDto.getQty());
        procced.setUnitPrice(actualPrice);


        return procced;
    }
}
