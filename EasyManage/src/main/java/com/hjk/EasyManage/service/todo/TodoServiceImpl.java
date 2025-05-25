package com.hjk.EasyManage.service.todo;

import com.hjk.EasyManage.dto.todo.TodoRequest;
import com.hjk.EasyManage.entity.Todo;
import com.hjk.EasyManage.entity.Users;
import com.hjk.EasyManage.repository.todo.TodoJpaRepository;
import com.hjk.EasyManage.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoJpaRepository todoJpaRepository;
    private final UserJpaRepository userJpaRepository;


    @Override
    public List<Todo> findByUserId(Long userId) {

        return todoJpaRepository.findByUser_Id(userId);
    }

    @Override
    public void save(TodoRequest todoRequest, Long userId) {
        // 1. 유저아이디를 통한 조회 방법
        Users getUser = userJpaRepository.findById(userId).get();

        Todo todo = new Todo();
        todo.setUser(getUser);
        todo.setCompleted(false);
        todo.setContent(todoRequest.getContent());
        todo.setFinishAt(todoRequest.getFinishAt());

        todoJpaRepository.save(todo);

    }
}
