package com.alansep.spring_batch_lab.model.dto;

import java.math.BigDecimal;

public record StudentScore(Long id, String firstName, String lastName, BigDecimal firstScore, BigDecimal secondScore, BigDecimal thirdScore, BigDecimal fourthScore) {

}
