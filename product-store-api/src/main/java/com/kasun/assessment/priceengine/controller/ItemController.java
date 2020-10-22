package com.kasun.assessment.priceengine.controller;

import com.kasun.assessment.priceengine.dto.PriceList;
import com.kasun.assessment.priceengine.dto.ItemDto;
import com.kasun.assessment.priceengine.dto.ShoppingCartDto;
import com.kasun.assessment.priceengine.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${app.endpoint.api}/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * Rest api end point for get price list for give item and qty range (from qty to to qty)
     *
     * @param id
     * @param fromQty
     * @param toQty
     * @return ResponseEntity
     */
    @GetMapping("/price-list/item/{id}/{fromQty}/{toQty}")
    public ResponseEntity<List> listAllPriceByItem(@PathVariable("id") Integer id,
                                                   @PathVariable("fromQty") Integer fromQty,
                                                   @PathVariable("toQty") Integer toQty) {

        List<PriceList> priceLists
                = itemService.listAllPriceByItem(id, fromQty, toQty);

        return new ResponseEntity<>(priceLists, HttpStatus.OK);

    }

    /** rest api end point for get actual price  and total qty for given item and qty
     * @param id
     * @param unitType
     * @param qty
     * @return
     */
    @GetMapping("/actual-price/{id}/{unitType}/{qty}")
    public ResponseEntity<List> calculateActualPrice(@PathVariable("id") Integer id,
                                                     @PathVariable("unitType") String unitType,
                                                     @PathVariable("qty") Integer qty) {

        HashMap values  = itemService.calculateActualPrice(id, unitType, qty);

        return new ResponseEntity(values, HttpStatus.OK);

    }

    /** rest api end point for find all items
     * @return ResponseEntity
     */
    @GetMapping("/")
    public ResponseEntity findAllItems() {
        List<ItemDto> all = itemService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }


    /**
     * rest api end point for calculating shopping cart calculations
     *
     * @param shoppingCartDto
     * @return ResponseEntity
     */
    @PostMapping("/")
    public ResponseEntity calculateShoppingCart(@RequestBody ShoppingCartDto shoppingCartDto) {
        ShoppingCartDto proccedCartDto = itemService.calculateShoppingCart(shoppingCartDto);
        return new ResponseEntity<>(proccedCartDto, HttpStatus.OK);
    }
}
