package com.hjk.EasyManage.dto.finance.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hjk.EasyManage.entity.FinanceType;
import lombok.Data;

@Data
public class FinanceCategoryRequest {
    private String categoryName;
    private FinanceType financeType;
    private Long userId;
}
