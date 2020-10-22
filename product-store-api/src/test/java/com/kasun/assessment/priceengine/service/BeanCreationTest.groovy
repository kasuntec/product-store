package com.kasun.assessment.priceengine.service

import com.kasun.assessment.priceengine.PriceengineApplication
import com.kasun.assessment.priceengine.dto.ItemDto
import com.kasun.assessment.priceengine.repository.ItemRepository
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@WebAppConfiguration
@ContextConfiguration(classes=[PriceengineApplication.class])
@Transactional
@SpringBootTest
class BeanCreationTest  extends Specification {

    @Autowired
    private ItemRepository itemRepository;

    def 'beans creation' () {
        expect: 'beans creation successfully'

        assert itemRepository!=null
    }
}
