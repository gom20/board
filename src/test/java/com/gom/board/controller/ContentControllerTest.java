package com.gom.board.controller;

import com.gom.board.constant.ErrorCode;
import com.gom.board.dto.ContentDTO;
import com.gom.board.dto.ContentDetailDTO;
import com.gom.board.dto.CreateContent;
import com.gom.board.error.GeneralException;
import com.gom.board.service.ContentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContentController.class)
class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @DisplayName("[API][GET] 게시글 리스트 조회")
    @Test
    void testGetAllContents() throws Exception {
        //given
        given(contentService.getAllContents())
                .willReturn(Arrays.asList(ContentDTO.builder()
                        .id(1L)
                        .name("user1")
                        .title("title1")
                        .build()));

        //when & then
        mockMvc.perform(get("/contents"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].name").value("user1"))
                .andExpect(jsonPath("$.data[0].title").value("title1"))
                .andExpect(jsonPath("$.code").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @DisplayName("[API][GET] 게시글 상세 조회")
    @Test
    void testGetContentDetail() throws Exception {
        //given
        given(contentService.getContentDetail(any()))
                .willReturn(ContentDetailDTO.builder()
                        .id(1L)
                        .name("user1")
                        .title("title1")
                        .content("content1")
                        .build());

        //when & then
        mockMvc.perform(get("/contents/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.name").value("user1"))
                .andExpect(jsonPath("$.data.title").value("title1"))
                .andExpect(jsonPath("$.data.content").value("content1"))
                .andExpect(jsonPath("$.code").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @DisplayName("[API][POST] 게시글 등록")
    @Test
    void testCreateContent() throws Exception {
        //given
        given(contentService.createContent(any()))
                .willReturn(CreateContent.Response.builder()
                        .name("user1")
                        .title("title1")
                        .content("content1")
                        .build());
        //when & then
        mockMvc.perform(post("/contents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(
                                "{ \"name\" : \"user1\", \"title\" : \"test1\", \"content\": \"content1\"}"
                        ))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.name").value("user1"))
                .andExpect(jsonPath("$.data.title").value("title1"))
                .andExpect(jsonPath("$.data.content").value("content1"))
                .andExpect(jsonPath("$.code").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @DisplayName("[API][PUT] 게시글 수정")
    @Test
    void testUpdateContent() throws Exception {
        //given
        given(contentService.updateContent(any(), any()))
                .willReturn(ContentDetailDTO.builder()
                        .name("user1")
                        .title("title2")
                        .content("content2")
                        .build());
        //when & then
        mockMvc.perform(put("/contents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(
                                "{ \"name\" : \"user1\", \"title\" : \"test2\", \"content\": \"content2\"}"
                        ))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.name").value("user1"))
                .andExpect(jsonPath("$.data.title").value("title2"))
                .andExpect(jsonPath("$.data.content").value("content2"))
                .andExpect(jsonPath("$.code").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @DisplayName("[API][DELETE] 게시글 삭제")
    @Test
    void testDeleteContent() throws Exception {
        //given
        given(contentService.deleteContent(any()))
                .willReturn(ContentDetailDTO.builder()
                        .name("user1")
                        .title("title2")
                        .content("content2")
                        .build());
        //when & then
        mockMvc.perform(delete("/contents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.name").value("user1"))
                .andExpect(jsonPath("$.data.title").value("title2"))
                .andExpect(jsonPath("$.data.content").value("content2"))
                .andExpect(jsonPath("$.code").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @DisplayName("[API][PUT][ERROR] 게시글 수정 / 제목 공란")
    @Test
    void givenInvalidParameter_whenRequestingUpdateContent_thenReturnsErorr() throws Exception {
        //given
        given(contentService.updateContent(any(), any()))
                .willThrow(new GeneralException(ErrorCode.BAD_REQUEST));
        //when & then
        mockMvc.perform(put("/contents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(
                                "{ \"name\" : \"user1\", \"content\": \"content2\"}"
                        ))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(ErrorCode.SPRING_INTERNAL_ERROR.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.SPRING_INTERNAL_ERROR.getMessage()));
    }

    @DisplayName("[API][POST][ERROR] 게시글 등록 / 이름 사이즈 초과")
    @Test
    void givenBlankName_whenRequestingCreateContent_thenReturnsErorr() throws Exception {
        //given
        given(contentService.createContent(any()))
                .willThrow(new GeneralException(ErrorCode.BAD_REQUEST));
        //when & then
        mockMvc.perform(post("/contents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(
                                "{ \"name\" : \"user1234567891011111\", \"title\" : \"test2\", \"content\": \"content2\"}"
                        ))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(ErrorCode.SPRING_INTERNAL_ERROR.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.SPRING_INTERNAL_ERROR.getMessage()));
    }
}