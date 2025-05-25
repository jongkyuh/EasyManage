package com.hjk.EasyManage.dto.todo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoRequest {

    private String content;
    private LocalDateTime finishAt;
}
