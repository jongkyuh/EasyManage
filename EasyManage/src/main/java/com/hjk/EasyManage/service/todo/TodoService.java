package com.hjk.EasyManage.service.todo;

import com.hjk.EasyManage.dto.todo.TodoRequest;
import com.hjk.EasyManage.dto.todo.TodoUpdateDto;
import com.hjk.EasyManage.dto.todo.TodoViewDto;
import com.hjk.EasyManage.dto.todo.TodoWithUserResponse;
import com.hjk.EasyManage.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TodoService {

    List<Todo> findByUser_IdOOrderByFinishAt(Long userId);

    Todo save(TodoRequest todoRequest, Long userId);

    void completedChange(Long todoId);

    void deleteTodo(Long todoId);

    List<TodoWithUserResponse> findAll();

    TodoViewDto findByTodoIdWithLoginId(Long todoId, Long userId);

    void updateTodo(TodoUpdateDto todoUpdateDto);
}
