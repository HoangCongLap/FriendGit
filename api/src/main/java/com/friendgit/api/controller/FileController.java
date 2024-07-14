package com.friendgit.api.controller;



import com.friendgit.api.entity.File;
import com.friendgit.api.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    // API để tạo một tệp mới
    @PostMapping("/create")
    public File createFile(@RequestBody File request) {
        return fileService.createFile(request);
    }


    // API để lấy tất cả các tệp
    @GetMapping
    public List<File> getAllFiles() {
        return fileService.getAllFiles();
    }

    // API để lấy một tệp theo ID
    @GetMapping("/{id}")
    public Optional<File> getFileById(@PathVariable String id) {
        return fileService.getFileById(id);
    }

    // API để cập nhật một tệp
    @PutMapping("/{id}")
    public File updateFile(@PathVariable String id, @RequestParam String repoId, @RequestParam String versionId, @RequestParam String fileName, @RequestParam String filePath, @RequestParam Date uploadedAt) {
        return fileService.updateFile(id, repoId, versionId, fileName, filePath, uploadedAt);
    }

    // API để xóa một tệp
    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable String id) {
        fileService.deleteFile(id);
    }
}
