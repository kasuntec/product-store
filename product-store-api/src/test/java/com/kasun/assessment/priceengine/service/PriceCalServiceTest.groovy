package com.kasun.assessment.priceengine.service

import com.kasun.assessment.priceengine.entity.Item
import com.kasun.assessment.priceengine.repository.ItemRepository
import com.kasun.assessment.priceengine.service.impl.PriceCalServiceImpl
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import javax.persistence.EntityNotFoundException

@SpringBootTest
class PriceCalServiceTest extends spock.lang.Specification {

    @Autowired
    private ItemService itemService;

    @Autowired
    private PriceCalService priceCalService;


    def "calculate unit price for given item with pass exits item ids and various qty"() {
        given:
        def item = itemService.findById(id);

        when:
        def resultUnitPrice = priceCalService.calculateUnitPrice(item, qty);

        then:
        resultUnitPrice == expectedUnitPrice;

        where:
        id  | qty | expectedUnitPrice
        1   | 0   | 0.00
        1   | -1  | 0.00
        1   | 1   | 11.375
        1   | 2   | 11.375
        1   | 20  | 8.75
        1   | 21  | 8.875
        1   | 40  | 8.75
        1   | 42  | 8.875
        1   | 50  | 9.275
        1   | 60  | 7.875
        1   | 65  | 8.14423076923077
        1   | 100 |  7.875
        2   | 1   |  214.5
        2   | 2   |  214.5
        2   | 5   |  165
        2   | 6   |  173.25
        2   | 10  |  165
        2   | 12  |  173.25
        2   | 20  |  148.5
        2   | 28  |  155.57142857142858
        2   | 35  |  148.5
        2   | 50  |  148.5

    }

    def "when try to get price using not exits item id"() {
        given: 'try to get item from item id'
        def id = 3
        def qty = 1

        when: 'calculate item price'
        def item = itemService.findById(id);
        def resultUnitPrice = priceCalService.calculateUnitPrice(item, qty);

        then: "should throw EntityNotFoundException"
        javax.persistence.EntityNotFoundException ex = thrown()
        ex.message == "Item not found"


    }

    def "pass null for item id"() {
        given: 'set null for item id'
        def id = null
        def qty = 1

        when: 'calculate item price'
        def item = itemService.findById(id);
        def resultUnitPrice = priceCalService.calculateUnitPrice(item, qty);

        then: "should throw EntityNotFoundException"
        javax.persistence.EntityNotFoundException ex = thrown()
        ex.message == "Item not found"


    }

    def "pass null for qty"() {
        given: 'set null for qty'
        def id = 1
        def qty = null

        when: 'calculate item price'
        def item = itemService.findById(id);
        def resultUnitPrice = priceCalService.calculateUnitPrice(item, qty);

        then: "should return 0"
        assert  resultUnitPrice == 0.00

    }



}
