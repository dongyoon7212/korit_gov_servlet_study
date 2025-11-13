package com.korit.korit_gov_servlet_study.ch02;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Todo {
    private String title;
    private String content;
    private String username;
}
