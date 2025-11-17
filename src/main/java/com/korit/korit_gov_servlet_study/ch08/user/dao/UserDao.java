package com.korit.korit_gov_servlet_study.ch08.user.dao;

import com.korit.korit_gov_servlet_study.ch08.user.entity.User;
import com.korit.korit_gov_servlet_study.ch08.user.util.ConnectionFactory;

import java.sql.*;
import java.util.Optional;

public class UserDao {
    private static UserDao instance;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }
    //user추가
    public User addUser(User user) {
        String sql = "insert into user_tb(user_id, username, password, age, create_dt) values (0, ?, ?, ?, now())";
        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());

            ps.execute();
            try(ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Integer userId = rs.getInt(1);
                    user.setUserId(userId);
                }
            }

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //username으로 유저찾기
    public Optional<User> findByUsername(String username) {
        String sql = "select user_id, username, password, age, create_dt from user_tb where username = ?";

        try (
                Connection con = ConnectionFactory.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, username);
            try(ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(toUser(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public User toUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .userId(resultSet.getInt("user_id"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .age(resultSet.getInt("age"))
                .createDt(resultSet.getTimestamp("create_dt").toLocalDateTime())
                .build();
    }
}







