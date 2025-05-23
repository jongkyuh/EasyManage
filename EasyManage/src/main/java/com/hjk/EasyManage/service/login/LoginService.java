package com.hjk.EasyManage.service.login;

import com.hjk.EasyManage.dto.user.LoginUserDto;
import com.hjk.EasyManage.entity.Users;

public interface LoginService {

    Users login(LoginUserDto loginUserDto);

}
