package com.korit.korit_gov_servlet_study.ch07;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.korit.korit_gov_servlet_study.ch03.SuccessResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ch07/users")
public class UserServlet extends HttpServlet {
    private Gson gson;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        gson = new GsonBuilder().create();
        userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String username = req.getParameter("username");
        if (username != null) {
            User user = userService.findByUsername(username);
            String json;
            SuccessResponse<User> successResponse;
            if (user != null) {
                successResponse = SuccessResponse.<User>builder()
                        .status(200)
                        .message(username + " 조회 완료")
                        .body(user)
                        .build();
            } else {
                successResponse = SuccessResponse.<User>builder()
                        .status(200)
                        .message(username + " 해당 username의 회원이 없습니다.")
                        .body(null)
                        .build();
            }
            json = gson.toJson(successResponse);
            resp.getWriter().write(json);
            return;
        }

        List<User> users = userService.getUserListAll();
        SuccessResponse<List<User>> successResponse = SuccessResponse.<List<User>>builder()
                .status(200)
                .message("전체 조회 완료")
                .body(users)
                .build();
        String json = gson.toJson(successResponse);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignupReqDto signupReqDto = gson.fromJson(req.getReader(), SignupReqDto.class);
        resp.setContentType("application/json");
        if (userService.isDuplicatedUsername(signupReqDto.getUsername())){
            SuccessResponse<String> successResponse = SuccessResponse.<String>builder()
                    .status(200)
                    .message("username이 중복되었습니다.")
                    .body(signupReqDto.getUsername())
                    .build();
            String json = gson.toJson(successResponse);
            resp.getWriter().write(json);
            return;
        }

        User user = userService.addUser(signupReqDto);
        SuccessResponse<User> successResponse = SuccessResponse.<User>builder()
                .status(200)
                .message("회원가입 완료")
                .body(user)
                .build();
        String json = gson.toJson(successResponse);
        resp.getWriter().write(json);
    }

}










