package com.hjk.EasyManage.exception.todo;

public class NotHasTodoException extends IllegalArgumentException{
    public NotHasTodoException(){
        super("해당하는 todolist가 없습니다.");
    }
}
