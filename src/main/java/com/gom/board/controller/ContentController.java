package com.gom.board.controller;

import com.gom.board.dto.*;
import com.gom.board.service.ContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("contents")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ContentController {

    private final ContentService contentService;

    @PostMapping("")
    public APIResponse createContent(@Valid @RequestBody final CreateContent.Request request){
        return APIResponse.of(contentService.createContent(request));
    }

    @GetMapping("")
    public APIResponse getAllContents(){
        return APIResponse.of(contentService.getAllContents());
    }

    @GetMapping("/{id}")
    public APIResponse getContentDetail(@PathVariable final Long id){
        return APIResponse.of(contentService.getContentDetail(id));
    }

    @PutMapping("/{id}")
    public APIResponse updateContent(@PathVariable final Long id, @Valid @RequestBody UpdateContent.Request request){
        return APIResponse.of(contentService.updateContent(id, request));
    }

    @DeleteMapping("/{id}")
    public APIResponse deleteContent(@PathVariable final Long id){
        return APIResponse.of(contentService.deleteContent(id));
    }

}
