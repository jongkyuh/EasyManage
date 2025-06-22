package com.hjk.EasyManage.dto.finance;

import com.hjk.EasyManage.entity.FinanceType;
import lombok.Data;

@Data
public class FinanceCategoryRequest {
    private String categoryName;
    private FinanceType fType;
    private Long userId;
}
