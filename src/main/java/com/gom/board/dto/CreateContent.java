package com.gom.board.dto;

import com.gom.board.entity.Content;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateContent {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request {
        @NotNull
        @Size(min = 1, max = 10, message = "name size must be 1 to 10")
        private String name;

        @NotNull
        @Size(min = 1, max = 50, message = "title size must be 1 to 50")
        private String title;

        @NotNull
        @Size(min = 1, max = 100, message = "content size must be 1 to 100")
        private String content;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String name;
        private String title;
        private String content;

        public static Response fromEntity(@NotNull Content content){
            return Response.builder()
                    .name(content.getName())
                    .title(content.getTitle())
                    .content(content.getContent())
                    .build();
        }
    }

}
