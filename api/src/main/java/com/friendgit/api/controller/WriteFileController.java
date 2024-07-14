package com.friendgit.api.controller;

import com.friendgit.api.model.Response;
import com.friendgit.api.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class WriteFileController {

    private final MediaService mediaService;

    @Autowired
    public WriteFileController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> uploadFileNew(@RequestParam("file") MultipartFile file) throws Exception {

        // Lưu tệp
        String path = mediaService.saveMediaFile(file);

        Response response = Response.builder()
                .code("SUCCESS_CODE")
                .message("File uploaded successfully")
                .data(path)
                .build();

        return ResponseEntity.ok(response);
    }
}
