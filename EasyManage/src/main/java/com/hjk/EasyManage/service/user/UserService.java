package com.hjk.EasyManage.service.user;

import com.hjk.EasyManage.dto.user.SignUpUserDto;
import com.hjk.EasyManage.entity.Role;
import com.hjk.EasyManage.entity.Users;
import com.hjk.EasyManage.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public void save(SignUpUserDto signUpUserDto){

        if(userJpaRepository.existsByUsername(signUpUserDto.getUsername())){
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
        Users user = new Users();
        user.setUsername(signUpUserDto.getUsername());
        user.setPassword(signUpUserDto.getPassword());
        user.setCreate_at(LocalDateTime.now());
        user.setRole(Role.BASIC);
        userJpaRepository.save(user);
    }
}
