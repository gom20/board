package com.gom.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ContentDetailDTO {
    private Long id;
    private String name;
    private String title;
    private String content;
    private LocalDateTime updatedAt;
}
