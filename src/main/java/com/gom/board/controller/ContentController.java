package com.gom.board.controller;

import com.gom.board.dto.ContentDTO;
import com.gom.board.dto.ContentDetailDTO;
import com.gom.board.dto.CreateContent;
import com.gom.board.dto.UpdateContent;
import com.gom.board.service.ContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("contents")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ContentController {

    private final ContentService contentService;

    @PostMapping("")
    public CreateContent.Response createContent(@Valid @RequestBody final CreateContent.Request request){
        return contentService.createContent(request);
    }

    @GetMapping("")
    public List<ContentDTO> getContents(){
        return contentService.getAllContents();
    }

    @GetMapping("/{id}")
    public ContentDetailDTO getContentDetail(@PathVariable final Long id){
        return contentService.getContentDetail(id);
    }

    @PutMapping("/{id}")
    public ContentDetailDTO updateContent(@PathVariable final Long id, @Valid @RequestBody UpdateContent.Request request){
        return contentService.updateContent(id, request);
    }

    @DeleteMapping("/{id}")
    public ContentDetailDTO deleteContent(@PathVariable final Long id){
        return contentService.deleteContent(id);
    }

}
