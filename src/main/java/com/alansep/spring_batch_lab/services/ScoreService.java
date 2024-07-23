package com.alansep.spring_batch_lab.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ScoreService {

    public BigDecimal calculateAverage(BigDecimal firstScore, BigDecimal secondScore, BigDecimal thirdScore, BigDecimal fourthScore) {
        return (firstScore.add(secondScore).add(thirdScore).add(fourthScore)).divide(BigDecimal.valueOf(4), 2, RoundingMode.DOWN);
    }

}
