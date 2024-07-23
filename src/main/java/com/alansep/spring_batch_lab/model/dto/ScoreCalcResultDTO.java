package com.alansep.spring_batch_lab.model.dto;

import java.math.BigDecimal;

public record ScoreCalcResultDTO(BigDecimal average, boolean isApprovedOnScoreRounding) {
}
