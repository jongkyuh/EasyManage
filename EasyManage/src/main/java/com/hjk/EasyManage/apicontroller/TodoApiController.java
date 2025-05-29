package com.hjk.EasyManage.apicontroller;

import com.hjk.EasyManage.service.todo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo/api")
@RequiredArgsConstructor
public class TodoApiController {

    private final TodoService todoService;

    // api 방식 완료여부 변경
    @PostMapping("/changecompleted/{todoId}")
    public ResponseEntity<Void> changeCompleted(@PathVariable("todoId") Long todoId){
        todoService.completedChange(todoId);

        return ResponseEntity.ok().build();
    }

    // api 방식 삭제
    @PostMapping("/deletetodo/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("todoId") Long todoId){
        todoService.deleteTodo(todoId);

        return ResponseEntity.ok().build();
    }
}
