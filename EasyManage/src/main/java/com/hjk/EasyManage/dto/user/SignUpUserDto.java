package com.hjk.EasyManage.dto.user;

import com.hjk.EasyManage.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Data
public class SignUpUserDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String username;
    private String password;

}
