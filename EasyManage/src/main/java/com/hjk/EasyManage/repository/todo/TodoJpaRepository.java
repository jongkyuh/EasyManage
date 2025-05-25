package com.hjk.EasyManage.repository.todo;

import com.hjk.EasyManage.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoJpaRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUser_Id(Long userId);
}
