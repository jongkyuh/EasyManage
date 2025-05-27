package com.hjk.EasyManage.service.todo;

import com.hjk.EasyManage.dto.todo.TodoRequest;
import com.hjk.EasyManage.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TodoService {

    List<Todo> findByUser_IdOOrderByFinishAt(Long userId);

    Todo save(TodoRequest todoRequest, Long userId);

    void completedChange(Long todoId);

    void deleteTodo(Long todoId);
}
