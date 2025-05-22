package com.hjk.EasyManage.service.login;

import com.hjk.EasyManage.dto.user.LoginUserDto;
import com.hjk.EasyManage.entity.Users;
import com.hjk.EasyManage.exception.login.UserNotFoundException;
import com.hjk.EasyManage.exception.login.WrongPasswordException;
import com.hjk.EasyManage.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder ;

    // 로그인
    public Users login(LoginUserDto loginUserDto){
        // 해당 유저 없으면 예외 발생
        Users getUser = userJpaRepository.findByUsername(loginUserDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException());

        // 비밀번호 일치여부
        if(passwordEncoder.matches(loginUserDto.getPassword(), getUser.getPassword())){
            return getUser;
        }else{
            throw new WrongPasswordException();
        }

//        if(loginUserDto.getPassword().equals(getUser.getPassword())){
//            return getUser;
//        }else{
//            throw new WrongPasswordException();
//        }




    }
}
