package com.korit.korit_gov_servlet_study.ch08.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiRespDto<T> {
    private String status;
    private String message;
    private T body;
}
