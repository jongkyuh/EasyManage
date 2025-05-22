package com.hjk.EasyManage.exception.user;

public class UserAlreadyExistsException extends IllegalArgumentException{
    public UserAlreadyExistsException(){
        super("이미 가입된 회원입니다.");
    }
}
