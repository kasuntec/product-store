package com.kasun.assessment.priceengine.service.impl;

import com.kasun.assessment.priceengine.common.ParametersKeys;
import com.kasun.assessment.priceengine.dto.ItemDto;
import com.kasun.assessment.priceengine.entity.Item;
import com.kasun.assessment.priceengine.repository.ParametersRepository;
import com.kasun.assessment.priceengine.service.PriceCalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PriceCalServiceImpl implements PriceCalService {

    @Autowired
    private ParametersRepository parametersRepository;

    /**
     * Service method for calculating unit price by given Qty of the item
     *
     * @param item of selected item
     * @param qty  of for calculating unit price
     * @return pricePerUnit of given item and qty
     */
    @Override
    public Double calculateUnitPrice(ItemDto item, Integer qty) {
        Double totalAmount = 0.0;
        Double actualUnitPrice = 0.0;

        // get parameters from DB
        Double singleUnitIncrese
                = parametersRepository.getOne(ParametersKeys.SINGLE_UNIT_PRICE_INCREASE_RATE.getValue()).getValue();

        Double discountFor3carton
                = parametersRepository.getOne(ParametersKeys.DIS_FOR_ABOVE_3_CARTON_PER.getValue()).getValue();

        Double totalDiscountAmount = 0.00;

        Integer noOfcarton = 0;

        if(qty == null || qty<1) {//check qty null or less than 1
            return actualUnitPrice;
        }

        if (qty < item.getUnitPerCarton()) {
            //single item bellow to cart items
            totalAmount = ((item.getCartonPrice() * singleUnitIncrese) / item.getUnitPerCarton()) * qty;
        } else {
            Integer singleItemCount = qty % item.getUnitPerCarton();
            if (singleItemCount == 0) {
                //cal price  to cart

                noOfcarton = qty / item.getUnitPerCarton();
                totalAmount = noOfcarton * item.getCartonPrice();
            } else {
                //above cart qty + single items
                Integer cartonQty = qty - singleItemCount;
                noOfcarton = cartonQty / item.getUnitPerCarton();

                //cal price for carton and single units
                totalAmount = ((item.getCartonPrice() * singleUnitIncrese) / item.getUnitPerCarton()) * singleItemCount;

                //cal for cartons
                totalAmount = totalAmount + (noOfcarton * item.getCartonPrice());

            }


            //calculate discount
            if (noOfcarton >= 3) {
                // carton grether than or equal 3
                Double discountPerCarton = (item.getCartonPrice() * discountFor3carton / 100);
                totalDiscountAmount = discountPerCarton * noOfcarton;

                totalAmount = totalAmount - totalDiscountAmount;

            }


        }


        // calculate unit price
        actualUnitPrice = totalAmount / qty;
        return actualUnitPrice;
    }
}
