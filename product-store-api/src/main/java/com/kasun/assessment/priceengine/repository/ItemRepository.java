package com.kasun.assessment.priceengine.repository;

import com.kasun.assessment.priceengine.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
