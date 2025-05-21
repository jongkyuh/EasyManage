package com.hjk.EasyManage.repository.user;

import com.hjk.EasyManage.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<Users, Long> {
}
