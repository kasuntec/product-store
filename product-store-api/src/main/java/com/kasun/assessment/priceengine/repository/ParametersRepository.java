package com.kasun.assessment.priceengine.repository;

import com.kasun.assessment.priceengine.entity.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParametersRepository extends JpaRepository<Parameters, String> {
}
