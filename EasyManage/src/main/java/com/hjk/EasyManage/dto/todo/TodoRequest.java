package com.hjk.EasyManage.dto.todo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoRequest {

    @NotBlank(message = "내용을 입력하세요.")
    private String content;
    @NotBlank(message = "완료예정날짜를 입력하세요.")
    private LocalDateTime finishAt;
}
