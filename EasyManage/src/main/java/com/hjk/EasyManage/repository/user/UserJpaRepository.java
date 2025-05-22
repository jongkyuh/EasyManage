package com.hjk.EasyManage.repository.user;

import com.hjk.EasyManage.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<Users, Long> {
    boolean existsByUsername(String username);

}
