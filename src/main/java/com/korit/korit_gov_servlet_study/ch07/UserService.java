package com.korit.korit_gov_servlet_study.ch07;

import java.util.List;

public class UserService {
    private static UserService instance;
    private UserRepository userRepository;

    private UserService() {
        userRepository = UserRepository.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean isDuplicatedUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return true;
    }

    public User addUser(SignupReqDto signupReqDto) {
        return userRepository.addUser(signupReqDto.toEntity());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUserListAll() {
        return userRepository.getUserListAll();
    }
}












