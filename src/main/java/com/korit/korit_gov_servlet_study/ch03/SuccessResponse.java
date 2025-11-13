package com.korit.korit_gov_servlet_study.ch03;

import lombok.Builder;

@Builder
public class SuccessResponse<T> {
    private int status = 200;
    private String message;
    private T body;
}
