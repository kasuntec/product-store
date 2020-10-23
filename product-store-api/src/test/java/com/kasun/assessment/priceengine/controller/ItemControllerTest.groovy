package com.kasun.assessment.priceengine.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest extends Specification {

    @Autowired
    private MockMvc mvcRestController;

    def "List all prices for itemid, from qty and to qty"() {

        given: 'pass data using path variables (itemid,from qty, to  to endpoint'
        def endpoint = "/api/item/price-list/item/1/1/50"

        expect: 'should return http 200 response and expected result'
        mvcRestController.perform(MockMvcRequestBuilders.get(endpoint))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().response.contentAsString.startsWith("[{\"unitPrice\":8.75,")
    }

    def "Calculate Actual Price for given item id, unit type and qty"() {
        given: 'pass data using path variables (itemid,unit type, qty) to endpoint'
        def endpoint = "/api/item/actual-price/1/Carton/1"

        expect: 'should return http 200 response and expected result'
        mvcRestController.perform(MockMvcRequestBuilders.get(endpoint))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().response.contentAsString == "{\"totalQty\":20,\"actualPrice\":8.75}"
    }

    def "find All Items"() {

        given: 'call endpoint with no parameters'
        def endpoint = "/api/item/"


        expect: 'should return http 200 response and not empty response'
        mvcRestController.perform(MockMvcRequestBuilders.get(endpoint))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().response.contentAsString!=""

    }

}
