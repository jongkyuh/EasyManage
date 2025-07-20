package com.hjk.EasyManage.dto.finance.list;

import com.hjk.EasyManage.entity.FinanceCategory;
import com.hjk.EasyManage.entity.FinanceType;
import lombok.Data;

@Data
public class FinanceListCreateDto {

    /**
     * create시 유저아이디, 카테고리, 가계부타입, 금액, 메모
     */

    private long userId;
    private long financeCategoryId;
    private FinanceType financeType;
    private int amount;
    private String memo;
}
