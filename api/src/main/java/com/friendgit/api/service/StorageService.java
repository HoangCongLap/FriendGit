package com.friendgit.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendgit.api.Util.ZipUtil;
import com.friendgit.api.entity.File;
import com.friendgit.api.exception.handleOrThrowException;
import com.friendgit.api.model.FileRequest;
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
import java.util.Optional;

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

        String fullFileName = (fileExtension == null || fileExtension.trim().isEmpty())
                ? fileName
                : fileName + "." + fileExtension;
        Path pathToSave = Paths.get(ROOT_REPOSITORY, fullFileName);

        createDirectory(pathToSave.getParent());

        try {
            ZipUtil.zipFile(file, pathToSave, fullFileName);

            String size = String.valueOf(Files.size(pathToSave));

            Date createdAt = new Date();
            String modifiedByUserId = null;

            boolean isFileExists = fileRepository.existsByProjectIdAndFileName(projectId, fileName);
            if (isFileExists) {
                modifiedByUserId = userId;
            }

            File fileEntity = createFile(projectId, fileName, size, userId, modifiedByUserId, pathToSave, createdAt);
            System.out.println(fileEntity);
        } catch (Exception e) {
            throw new handleOrThrowException("An error while saving the file: " + e.getMessage(), e);
        }

        return pathToSave.toString();
    }

    public byte[] readFile(FileRequest request) throws IOException {
        Optional<String> jsonPath = fileRepository.findFilePathByProjectIdAndFileName(request.getProjectId(), request.getFileName());

        if (jsonPath.isPresent()) {
            String json = jsonPath.get();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode filePathNode = rootNode.get("file_path");

            if (filePathNode != null) {
                String fullPath = filePathNode.asText();
                System.out.println("Path found: " + fullPath);

                Path pathToRead = Paths.get(fullPath);
                if (Files.exists(pathToRead) && Files.isReadable(pathToRead)) {
                    return ZipUtil.unzipFile(pathToRead);
                } else {
                    throw new IOException("File does not exist or is not readable: " + pathToRead);
                }
            } else {
                throw new IOException("Unable to extract file_path from response JSON");
            }
        } else {
            throw new IOException("Path not found for projectId: " + request.getProjectId() + " and fileName: " + request.getFileName());
        }
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
