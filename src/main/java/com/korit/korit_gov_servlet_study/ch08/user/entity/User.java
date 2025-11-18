package com.korit.korit_gov_servlet_study.ch08.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer userId;
    private String username;
    private String password;
    private int age;
    @JsonFormat(pattern = "YYYY-MM-dd kk:mm:ss")
    private LocalDateTime createDt;
}
