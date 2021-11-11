package com.gom.board.service;

import com.gom.board.dto.ContentDTO;
import com.gom.board.dto.ContentDetailDTO;
import com.gom.board.dto.CreateContent;
import com.gom.board.dto.UpdateContent;
import com.gom.board.entity.Content;
import com.gom.board.exception.GeneralException;
import com.gom.board.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.gom.board.constant.Constants.DELETE_N;
import static com.gom.board.constant.Constants.DELETE_Y;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<ContentDTO> getAllContents() {
        return contentRepository.findContentsByDeleteYNEquals(DELETE_N).stream()
                .map(content -> modelMapper.map(content, ContentDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public CreateContent.Response createContent(CreateContent.Request request) {
        return modelMapper.map(
                contentRepository.save(modelMapper.map(request, Content.class)),
                CreateContent.Response.class);

    }

    @Transactional
    public ContentDetailDTO getContentDetail(Long id) {
        return modelMapper.map(getDeveloperByMemberId(id), ContentDetailDTO.class);
    }

    @Transactional
    public ContentDetailDTO updateContent(Long id, UpdateContent.Request request) {
        Content content = getDeveloperByMemberId(id);
        content.setTitle(request.getTitle());
        content.setContent(request.getContent());
        return modelMapper.map(content, ContentDetailDTO.class);
    }

    @Transactional
    public ContentDetailDTO deleteContent(Long id) {
        Content content = getDeveloperByMemberId(id);
        content.setDeleteYN(DELETE_Y);
        return modelMapper.map(content, ContentDetailDTO.class);
    }

    private Content getDeveloperByMemberId(Long id){
        return contentRepository.findByIdAndDeleteYN(id, DELETE_N).orElseThrow(() -> new GeneralException());
    }

}
