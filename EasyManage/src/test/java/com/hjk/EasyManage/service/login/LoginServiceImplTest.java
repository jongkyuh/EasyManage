package com.hjk.EasyManage.service.login;

import com.hjk.EasyManage.dto.user.LoginUserDto;
import com.hjk.EasyManage.entity.Users;
import com.hjk.EasyManage.exception.login.UserNotFoundException;
import com.hjk.EasyManage.exception.login.WrongPasswordException;
import com.hjk.EasyManage.repository.user.UserJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceImplTest {

    @Autowired LoginService loginService;

    @Autowired UserJpaRepository userJpaRepository;

    @Test
    void  로그인_예외발생(){
        LoginUserDto testDto = new LoginUserDto();
        testDto.setUsername("없는유저이름");

        assertThrows(UserNotFoundException.class, () -> {
            loginService.login(testDto);
        });

    }

    @Test
    void 비밀번호_틀릴때(){
        LoginUserDto testDto = new LoginUserDto();
        testDto.setUsername("함종규");
        testDto.setPassword("틀린비밀번호");
        assertThrows(WrongPasswordException.class, () -> {
            loginService.login(testDto);
        });
    }

    @Test
    void 로그인_성공(){
        LoginUserDto testDto = new LoginUserDto();
        testDto.setUsername("함종규");
        testDto.setPassword("1111");
        Users loginUser = loginService.login(testDto);
        Users getUser = userJpaRepository.findByUsername(testDto.getUsername()).orElseThrow(() -> new UserNotFoundException());

        Assertions.assertThat(loginUser).isEqualTo(getUser);
    }

}