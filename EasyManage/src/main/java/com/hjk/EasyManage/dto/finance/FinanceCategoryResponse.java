package com.hjk.EasyManage.dto.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hjk.EasyManage.entity.FinanceType;
import com.hjk.EasyManage.entity.Users;
import lombok.Data;

@Data
public class FinanceCategoryResponse {
    private Long categoryId;
    private Users user;
    private String categoryName;
    private FinanceType financeType;
}
