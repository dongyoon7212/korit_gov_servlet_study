package com.korit.korit_gov_servlet_study.ch08.user.service;

import com.korit.korit_gov_servlet_study.ch08.user.dao.UserDao;
import com.korit.korit_gov_servlet_study.ch08.user.dto.SignupReqDto;
import com.korit.korit_gov_servlet_study.ch08.user.entity.User;

import java.util.Optional;

public class UserService {
    private static UserService instance;
    private UserDao userDao;

    private UserService() {
        userDao = UserDao.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public boolean isDuplicatedUsername(String username) {
        //중복인지 아닌지 판단하려면 가져온 username의 값을 가지고 있는 유저가 있는지 확인
        Optional<User> foundUser = userDao.findByUsername(username);
        return foundUser.isPresent();
    }

    //추가하는 메소드
    public User addUser(SignupReqDto signupReqDto) {
        //여기서는 딱히 비즈니스 로직이 필요가 없다 그냥 추가하면 된다.
        return userDao.addUser(signupReqDto.toEntity());
    }
}
