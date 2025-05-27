package com.hjk.EasyManage.service.todo;

import com.hjk.EasyManage.dto.todo.TodoRequest;
import com.hjk.EasyManage.entity.Todo;
import com.hjk.EasyManage.entity.Users;
import com.hjk.EasyManage.repository.todo.TodoJpaRepository;
import com.hjk.EasyManage.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService{

    private final TodoJpaRepository todoJpaRepository;
    private final UserJpaRepository userJpaRepository;


    @Override
    public List<Todo> findByUser_IdOOrderByFinishAt(Long userId) {

        return todoJpaRepository.findByUser_IdOrderByFinishAt(userId);
    }

    @Override
    public Todo save(TodoRequest todoRequest, Long userId) {
        // 1. 유저아이디를 통한 조회 방법
        Users getUser = userJpaRepository.findById(userId).get();

        Todo todo = new Todo();
        todo.setUser(getUser);
        todo.setCompleted(false);
        todo.setContent(todoRequest.getContent());
        todo.setFinishAt(todoRequest.getFinishAt());

        return todoJpaRepository.save(todo);

    }

    // todo 여부 변경하기
    @Override
    public void completedChange(Long todoId) {
        // 변경감지 통해 변경하기
        Todo todo = todoJpaRepository.findById(todoId).orElseThrow(() -> new IllegalArgumentException("해당 목록 없음!!"));
        todo.setCompleted(!todo.isCompleted());
    }

    // todo 삭제
    @Override
    public void deleteTodo(Long todoId) {
        todoJpaRepository.deleteById(todoId);
    }
}
