package com.hjk.EasyManage.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FinanceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    private String categoryName;

    @Enumerated(EnumType.STRING)
    private FinanceType fType;
}