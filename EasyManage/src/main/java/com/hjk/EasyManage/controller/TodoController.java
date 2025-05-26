package com.hjk.EasyManage.controller;

import com.hjk.EasyManage.dto.todo.TodoRequest;
import com.hjk.EasyManage.dto.todo.TodoViewDto;
import com.hjk.EasyManage.entity.Todo;
import com.hjk.EasyManage.repository.user.UserJpaRepository;
import com.hjk.EasyManage.service.todo.TodoService;
import com.hjk.EasyManage.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;
    private final UserService userService;

    @GetMapping("")
    public String todo(HttpSession httpSession, Model model){

        Long loginId = (Long) httpSession.getAttribute("loginId");
        if (loginId == null) {
            // 세션에 로그인ID 없으면 로그인 페이지로 리다이렉트
            return "redirect:/";
        }

        List<Todo> todoList = todoService.findByUser_IdOOrderByFinishAt((Long) httpSession.getAttribute("loginId"));

        List<TodoViewDto> todoViewDto = todoList.stream()
                .map(todo -> {
                    TodoViewDto dto = new TodoViewDto();
                    dto.setId(todo.getId());
                    dto.setCompleted(todo.isCompleted());
                    dto.setContent(todo.getContent());
                    dto.setFinishAt(todo.getFinishAt());
                    return dto;
                })
                .collect(Collectors.toList());

        model.addAttribute("todoForm", new TodoRequest());
        model.addAttribute("todoViewDto", todoViewDto);
        return "todo/todo";
    }

    @PostMapping("/inserttodo")
    public String insertTodo(@ModelAttribute("todoForm") TodoRequest todoRequest,
                             HttpSession httpSession, Model model){

        todoService.save(todoRequest,(Long)httpSession.getAttribute("loginId"));

        return "redirect:/todo";


    }

    @PostMapping("/changecompleted/{todoId}")
    public String changeCompleted(@PathVariable("todoId") Long todoId){
        todoService.completedChange(todoId);

        return "redirect:/todo";
    }
}
