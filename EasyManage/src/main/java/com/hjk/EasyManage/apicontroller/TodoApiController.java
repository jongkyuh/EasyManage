package com.hjk.EasyManage.apicontroller;

import com.hjk.EasyManage.dto.todo.TodoWithUserResponse;
import com.hjk.EasyManage.entity.Todo;
import com.hjk.EasyManage.service.todo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // api 방식 단순 Todo 전체조회
    @GetMapping("/findall")
    public ResponseEntity<List<TodoWithUserResponse>> findAll(){
        List<TodoWithUserResponse> all = todoService.findAll();
        return ResponseEntity.ok().body(all);
    }
}
