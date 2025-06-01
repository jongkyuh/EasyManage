package com.hjk.EasyManage.dto.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TodoUpdateDto {


    private Long id;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "종료예정일자를 입력해주세요")
    private LocalDateTime finishAt;
}
