package com.gom.board.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    OK(0, "요청이 성공하였습니다."),
    BAD_REQUEST(10000, "잘못된 요청입니다."),
    INTERNAL_ERROR(20000, "서버 오류가 발생하였습니다."),
    SPRING_INTERNAL_ERROR(20001, "Spring 오류가 발생하였습니다.");

    private final Integer code;
    private final String message;

    public String getMessage(Exception e){
        return getMessage(this.getMessage() + "-" + e.getMessage());
    }

    public String getMessage(String message){
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(getMessage());
    }
}