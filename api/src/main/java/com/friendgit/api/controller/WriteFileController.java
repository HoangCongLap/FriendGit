package com.friendgit.api.controller;

import com.friendgit.api.model.Response;
import com.friendgit.api.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class WriteFileController {

    private static final String ROOTT_REPOSITORY = "C:/Users/Hoang Cong Lap/Desktop/test";
    private final MediaService mediaService;

    @Autowired
    public WriteFileController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @RequestMapping(value = "/upload/file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> uploadFileNew(@RequestParam("file") MultipartFile file) throws Exception {

        String path = mediaService.saveMediaFile(file);

        Response response = Response.builder()
                .code("SUCCESS_CODE")
                .message("File uploaded successfully")
                .data(path)
                .build();

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/download/file/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] data = mediaService.getMediaFile(fileName);

        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(data.length)
                .body(resource);
    }
}
