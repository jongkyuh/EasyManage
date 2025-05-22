package com.hjk.EasyManage.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {

    @NotBlank
    private String username;
    private String password;
}
