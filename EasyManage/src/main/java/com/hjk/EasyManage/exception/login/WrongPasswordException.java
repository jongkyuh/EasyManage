package com.hjk.EasyManage.exception.login;

public class WrongPasswordException extends IllegalArgumentException{

    public WrongPasswordException(){
        super("비밀번호가 틀렸습니다.");
    }
}
