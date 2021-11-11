package com.gom.board.dto;

import com.gom.board.entity.Content;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ContentDTO {
    private Long id;
    private String name;
    private String title;
    private LocalDateTime updatedAt;
}
