package com.hjk.EasyManage.repository.user;

import com.hjk.EasyManage.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<Users, Long> {
    boolean existsByUsername(String username);  // 유저이름으로 존재 유무
    Optional<Users> findByUsername(String username);    // 유저이름으로 유저찾기



}
