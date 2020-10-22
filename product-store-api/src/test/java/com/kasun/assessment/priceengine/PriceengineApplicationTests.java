package com.kasun.assessment.priceengine;

import com.kasun.assessment.priceengine.dto.ItemDto;
import com.kasun.assessment.priceengine.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest
class PriceengineApplicationTests {
    
    @Autowired
    private ItemService itemService;

    @Test
    void contextLoads() {

        itemService.findAll();

    }

}
