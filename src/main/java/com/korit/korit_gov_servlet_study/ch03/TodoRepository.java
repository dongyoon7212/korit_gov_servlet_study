package com.korit.korit_gov_servlet_study.ch03;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private static TodoRepository instance;
    private List<Todo> todos;
    private Integer todoId = 1;

    private TodoRepository() {
        todos = new ArrayList<>();
    }

    public static TodoRepository getInstance() {
        if (instance == null) {
            instance = new TodoRepository();
        }
        return instance;
    }

    public Todo addTodo(Todo todo) {
        todo.setTodoId(todoId++);
        todos.add(todo);
        return todo;
    }

    public List<Todo> findAll() {
        return todos;
    }

    public Todo findTodoByTitle(String title) {
        return todos.stream()
                .filter(todo -> todo.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }
}










