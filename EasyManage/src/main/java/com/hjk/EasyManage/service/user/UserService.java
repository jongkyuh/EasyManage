package com.hjk.EasyManage.service.user;

import com.hjk.EasyManage.dto.user.SignUpUserDto;
import com.hjk.EasyManage.entity.Users;

public interface UserService {

    Users save(SignUpUserDto signUpUserDto); // 유저등록

    Users findByUsername(String username);  // 유저이름으로 유저찾기

    Users findByUserId(Long id);    // 유저아이디로 유저찾기


}
