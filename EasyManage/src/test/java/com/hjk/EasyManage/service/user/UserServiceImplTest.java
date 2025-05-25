package com.hjk.EasyManage.service.user;

import com.hjk.EasyManage.dto.user.SignUpUserDto;
import com.hjk.EasyManage.entity.Users;
import com.hjk.EasyManage.repository.user.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    void 기본유저생성(){
        SignUpUserDto signUpUserDto = new SignUpUserDto();
        signUpUserDto.setUsername("기본유저");
        signUpUserDto.setPassword("1111");

        userServiceImpl.save(signUpUserDto);
    }
    @Test
    void 회원_생성(){

        SignUpUserDto signUpUserDto = new SignUpUserDto();
        signUpUserDto.setUsername("함종규");
        signUpUserDto.setPassword("1111");

        userServiceImpl.save(signUpUserDto);   // 첫번 째 저장
        Users getUser = userServiceImpl.findByUsername(signUpUserDto.getUsername());    // db에서 찾은값

        assertThat(signUpUserDto.getUsername()).isEqualTo(getUser.getUsername());


    }

    @Test
    void 중복회원_검사(){
        SignUpUserDto signUpUserDto = new SignUpUserDto();
        signUpUserDto.setUsername("기본유저");
        signUpUserDto.setPassword("1111");

        assertThrows(IllegalArgumentException.class, () -> {
            userServiceImpl.save(signUpUserDto);
        });
    }

    @Test
    void 회원이름으로찾기(){

    }
}