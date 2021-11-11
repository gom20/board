package com.gom.board.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateContent {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotNull
        @Size(min = 1, max = 50, message = "title size must be 1 to 50")
        private String title;

        @NotNull
        @Size(min = 1, max = 100, message = "content size must be 1 to 100")
        private String content;
    }
}
