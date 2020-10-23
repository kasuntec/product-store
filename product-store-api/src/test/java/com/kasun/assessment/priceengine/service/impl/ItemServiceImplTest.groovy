package com.kasun.assessment.priceengine.service.impl


import com.kasun.assessment.priceengine.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@SpringBootTest
@Transactional
class ItemServiceImplTest extends Specification {

    @Autowired
    private ItemService itemService;



    def "find by id"() {
        given: 'item id'
            def itemId = 1

        when: 'find by id with id = 1'
        def item = itemService.findById(itemId)

        then:
         assert item!=null

    }

}
