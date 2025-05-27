package com.hjk.EasyManage.service.todo;

import com.hjk.EasyManage.dto.todo.TodoRequest;
import com.hjk.EasyManage.entity.Todo;
import com.hjk.EasyManage.repository.todo.TodoJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodoServiceImplTest {

    @Autowired TodoService todoService;
    @Autowired
    TodoJpaRepository todoJpaRepository;

    @Test
    void 유저_투두리스트(){
        // 테스트 유저 아이디: 7 -> 리스트사이즈: 3
        List<Todo> getTodoList = todoService.findByUser_IdOOrderByFinishAt(7L);
        Assertions.assertThat(getTodoList.size()).isEqualTo(3);
    }

    @Test
    void 투두리스트_생성(){
        // 테스트 유저 아이디: 7
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setContent("테스트내용");
        todoRequest.setFinishAt(LocalDateTime.now());

        Todo saveTodo = todoService.save(todoRequest, 7L);
        Todo getTodo = todoJpaRepository.findById(saveTodo.getId()).get();

        Assertions.assertThat(saveTodo).isEqualTo(getTodo);

    }

    @Test
    void 완료여부_변경(){
        // 테스트 유저 아이디: 7
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setContent("테스트내용");
        todoRequest.setFinishAt(LocalDateTime.now());

        Todo saveTodo = todoService.save(todoRequest, 7L);

        // false -> true
        todoService.completedChange(saveTodo.getId());

        Assertions.assertThat(saveTodo.isCompleted()).isTrue();
    }

    @Test
    void 투두리스트_삭제(){
        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setContent("테스트내용");
        todoRequest.setFinishAt(LocalDateTime.now());

        Todo save = todoService.save(todoRequest, 7L);

        Long saveId = save.getId();

        todoService.deleteTodo(saveId);

        assertFalse(todoJpaRepository.existsById(saveId));
    }

}