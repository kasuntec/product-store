package com.kasun.assessment.priceengine.service


import com.kasun.assessment.priceengine.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@Transactional
@SpringBootTest
class BeanCreationTest  extends Specification {

    @Autowired
    private ItemRepository itemRepository;

    def 'beans creation test' () {
        expect: 'beans creation successfully'

        assert itemRepository!=null
    }
}
