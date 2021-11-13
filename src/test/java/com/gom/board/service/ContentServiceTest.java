package com.gom.board.service;

import com.gom.board.constant.ErrorCode;
import com.gom.board.dto.CreateContent;
import com.gom.board.dto.UpdateContent;
import com.gom.board.entity.Content;
import com.gom.board.error.GeneralException;
import com.gom.board.repository.ContentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContentServiceTest {

    @Mock
    private ContentRepository contentRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private ContentService contentService;

    private CreateContent.Request createRequest = CreateContent.Request.builder()
            .name("createTest")
            .title("title1")
            .content("content1")
            .build();

    private UpdateContent.Request updateRequest = UpdateContent.Request.builder()
            .title("updateTitle")
            .content("updateContent")
            .build();

    private Content content = Content.builder()
            .name("createTest")
            .title("title1")
            .content("content1")
            .build();

    @DisplayName("[CreateContent][Success] 게시글 작성 ")
    @Test
    void givenNothing_whenRequestingUpdateContent_thenReturnsContent(){
        // given
        ArgumentCaptor<Content> captor = ArgumentCaptor.forClass(Content.class);
        given(contentRepository.save(any()))
                .willReturn(content);

        // when
        contentService.createContent(createRequest);

        // then
        verify(contentRepository, times(1))
                .save(captor.capture());
        Content savedContent = captor.getValue();
        assertEquals(createRequest.getName(), savedContent.getName());
        assertEquals(createRequest.getTitle(), savedContent.getTitle());
        assertEquals(createRequest.getContent(), savedContent.getContent());
    }


    @DisplayName("[updateContent][Error] 존재하지 않는 ID로 게시글 업데이트 시도")
    @Test
    void givenNotExistedId_whenRequestingUpdateContent_thenReturnsError(){
        // given
        given(contentRepository.findByIdAndDeleteYN(any(), any()))
                .willReturn(Optional.empty());

        // when & then
        GeneralException generalException = assertThrows(GeneralException.class,
                () -> contentService.updateContent(1L, updateRequest));

        assertEquals(ErrorCode.BAD_REQUEST, generalException.getErrorCode());

    }
}