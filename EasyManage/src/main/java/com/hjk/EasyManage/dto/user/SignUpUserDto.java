package com.hjk.EasyManage.dto.user;

import com.hjk.EasyManage.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SignUpUserDto {

    private String username;
    private String password;

}
