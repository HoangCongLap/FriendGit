package com.friendgit.api.service;

import com.friendgit.api.entity.File;
import com.friendgit.api.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public File createFile(File request) {
        File file = new File();
        file.setFileName(request.getFileName());
        file.setFilePath(request.getFilePath());
        file.setVersionId(request.getVersionId());
        file.setCreateAt(request.getCreateAt());
        return fileRepository.save(file);
    }


    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }


    public Optional<File> getFileById(String id) {
        return fileRepository.findById(id);
    }


    public File updateFile(String id, String repoId, String versionId, String fileName, String filePath, Date createAt) {
        Optional<File> optionalFile = fileRepository.findById(id);
        if (optionalFile.isPresent()) {
            File file = optionalFile.get();
            file.setVersionId(versionId);
            file.setFileName(fileName);
            file.setFilePath(filePath);
            file.setCreateAt(createAt);
            return fileRepository.save(file);
        }
        return null;
    }

    // Xóa tệp
    public void deleteFile(String id) {
        fileRepository.deleteById(id);
    }
}
