package com.friendgit.api.service;

import com.friendgit.api.entity.File;
import com.friendgit.api.exception.handleOrThrowException;
import com.friendgit.api.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class StorageService {

    @Value("${root.repository}")
    private String ROOT_REPOSITORY;

    @Autowired
    private FileRepository fileRepository;

    public String writeFile(String projectId, String userId, String fileName, InputStream file, String fileExtension) throws Exception {
        if (userId == null || userId.trim().isEmpty()) {
            throw new handleOrThrowException("User ID must not be empty");
        }

        if (file == null || file.available() == 0) {
            throw new handleOrThrowException("File must not be empty");
        }

        if (fileName == null || fileName.trim().isEmpty()) {
            throw new handleOrThrowException("File name must not be empty");
        }

        if (fileExtension == null || fileExtension.trim().isEmpty()) {
            throw new handleOrThrowException("File extension must not be empty");
        }

        Path pathToSave = Paths.get(ROOT_REPOSITORY, fileName + "." + fileExtension);

        createDirectory(pathToSave.getParent());

        try {
            byte[] bytes = file.readAllBytes();

            String size = null;
            Date createdAt = new Date();
            String modifiedByUserId = null;

            boolean isFileExists = fileRepository.existsByProjectIdAndFileName(projectId, fileName);
            if (isFileExists) {
                modifiedByUserId = userId;
            }
            Files.write(pathToSave, bytes);

            File fileEntity = createFile(projectId, fileName, size, userId, modifiedByUserId, pathToSave, createdAt);
            System.out.println(fileEntity);
        } catch (Exception e) {
            throw new handleOrThrowException("An error while saving the file: " + e.getMessage(), e);
        }

        return pathToSave.toString();
    }

    public byte[] readFile(String fileName) throws IOException {
        Path pathToRead = Paths.get(ROOT_REPOSITORY, fileName);
        return Files.readAllBytes(pathToRead);
    }

    private void createDirectory(Path dirPath) throws Exception {
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }
    }

    private File createFile(String projectId, String fileName, String size, String createdByUserId, String modifiedByUserId, Path
            pathToSave, Date createdAt) {
        File file = new File();
        file.setProjectId(projectId);
        file.setFileName(fileName);
        file.setSize(size);
        file.setCreatedByUserId(createdByUserId);
        file.setModifiedByUserId(modifiedByUserId);
        file.setPath(String.valueOf(pathToSave));
        file.setCreateAt(createdAt);
        return fileRepository.save(file);
    }

}
