package com.hjk.EasyManage.dto.todo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoWithUserResponse {

    private Long id;

    private String content;

    private boolean completed;

    private LocalDateTime finishAt;

    private String username;
}
