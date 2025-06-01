package com.hjk.EasyManage.repository.todo;

import com.hjk.EasyManage.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoJpaRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUser_IdOrderByFinishAt(Long userId);

    @Query("select t from Todo t join fetch t.user")
    List<Todo> findAll();
}
