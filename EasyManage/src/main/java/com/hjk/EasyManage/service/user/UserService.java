package com.hjk.EasyManage.service.user;

import com.hjk.EasyManage.dto.user.SignUpUserDto;
import com.hjk.EasyManage.entity.Users;

public interface UserService {

    void save(SignUpUserDto signUpUserDto); // 유저등록

    Users findByUsername(String username);  // 유저찾기
}
