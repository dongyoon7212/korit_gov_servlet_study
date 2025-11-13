package com.korit.korit_gov_servlet_study.ch03;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/ch03/todos")
public class TodoServlet extends HttpServlet {
    private TodoRepository todoRepository;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        todoRepository = TodoRepository.getInstance();
        gson = new GsonBuilder().create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        List<Todo> todos = todoRepository.findAll();
        SuccessResponse<List<Todo>> successResponse = SuccessResponse.<List<Todo>>builder()
                .status(200)
                .message("전체 조회 완료")
                .body(todos)
                .build();
        String json = gson.toJson(successResponse);
        resp.setContentType("application/json");
        resp.setStatus(200);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        TodoDto todoDto = gson.fromJson(req.getReader(), TodoDto.class);

        Todo foundTodo = todoRepository.findTodoByTitle(todoDto.getTitle());

        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (foundTodo != null) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(400)
                    .message("제목이 중복되었습니다.")
                    .build();
            String json = gson.toJson(errorResponse);
            resp.setStatus(400);
            resp.getWriter().write(json);
            return;
        }

        Todo todo = todoRepository.addTodo(todoDto.toEntity());

        SuccessResponse<Todo> successResponse = SuccessResponse.<Todo>builder()
                .status(200)
                .message("뚜두가 정상적으로 등록이 되었습니다.")
                .body(todo)
                .build();
        String json = gson.toJson(successResponse);
        resp.setStatus(200);
        resp.getWriter().write(json);
    }

}
