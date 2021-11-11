package com.gom.board.dto;

import com.gom.board.constant.ErrorCode;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ErrorResponse {
    private final Integer code;
    private final String message;

    public static ErrorResponse of(Integer code, String message) {
        return new ErrorResponse(code, message);
    }
}
