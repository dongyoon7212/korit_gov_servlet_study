package com.korit.korit_gov_servlet_study.ch07;

import lombok.Builder;

@Builder
public class ResponseDto<T> {
    private String status;
    private String message;
    private T body;
}
