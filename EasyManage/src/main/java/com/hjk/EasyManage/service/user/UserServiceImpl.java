package com.hjk.EasyManage.service.user;

import com.hjk.EasyManage.dto.user.SignUpUserDto;
import com.hjk.EasyManage.entity.Role;
import com.hjk.EasyManage.entity.Users;
import com.hjk.EasyManage.exception.user.UserAlreadyExistsException;
import com.hjk.EasyManage.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    // 유저생성
    @Override
    public Users save(SignUpUserDto signUpUserDto){

        if(userJpaRepository.existsByUsername(signUpUserDto.getUsername())){
            throw new UserAlreadyExistsException();
        }
        Users user = new Users();
        user.setUsername(signUpUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpUserDto.getPassword()));
        user.setCreateAt(LocalDateTime.now());
        user.setRole(Role.BASIC);
        return userJpaRepository.save(user);
    }

    // 회원이름으로 유저찾기
    @Override
    public Users findByUsername(String username){
        return userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
    }

    @Override
    public Users findByUserId(Long id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
    }


}
