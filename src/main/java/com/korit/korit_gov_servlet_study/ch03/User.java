package com.korit.korit_gov_servlet_study.ch03;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String name;
    private String email;
}
