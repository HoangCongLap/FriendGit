package com.friendgit.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class MediaService {

    private final StorageService storageService;

    @Autowired
    public MediaService(StorageService storageService) {
        this.storageService = storageService;
    }

    public String saveMediaFile(MultipartFile file) throws Exception {
     //   String baseDir = "/user_files/";
      //  String fileLocation = storageService.getFileLocation(baseDir, new Date());

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new Exception("File must have a name");
        }

        String fileExtension = getFileExtension(originalFilename);
        String fileName = getFileNameWithoutExtension(originalFilename);

        try (InputStream inputStream = file.getInputStream()) {
            return storageService.writeFile( fileName, inputStream, fileExtension);
        }
    }

    // kiểm tra phần định dạng file ví dụ .pdf,.doc
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }

    private String getFileNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return fileName;
        }
        return fileName.substring(0, dotIndex);
    }
}
