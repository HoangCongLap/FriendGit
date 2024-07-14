package com.friendgit.api.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    private static final String ROOT_REPOSITORY = "C:/Users/Hoang Cong Lap/Desktop/test";

    public String writeFile(String fileName, InputStream file, String fileExtension) throws Exception {
        Path pathToSave = Paths.get(ROOT_REPOSITORY, fileName + "." + fileExtension);

        createDirectory(pathToSave.getParent());

        try {
            byte[] bytes = file.readAllBytes();
            Files.write(pathToSave, bytes);
        } catch (Exception e) {
            throw new Exception("An error while saving the file: " + e.getMessage(), e);
        }

        return pathToSave.toString();
    }

    private void createDirectory(Path dirPath) throws Exception {
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }
    }
}
