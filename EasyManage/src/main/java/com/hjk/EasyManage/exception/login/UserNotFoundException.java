package com.hjk.EasyManage.exception.login;

public class UserNotFoundException extends IllegalArgumentException{
    public UserNotFoundException(){
        super("존재하지 않는 유저입니다.");
    }
}
