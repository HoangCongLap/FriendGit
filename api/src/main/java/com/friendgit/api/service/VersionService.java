package com.friendgit.api.service;

import com.friendgit.api.entity.Version;
import com.friendgit.api.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VersionService {
    @Autowired
    private VersionRepository versionRepository;

    public Version createVersion(String projectId, String versionName) {
        Version version = new Version();
        version.setProjectId(projectId);
        version.setVersionName(versionName);
        return versionRepository.save(version);
    }
    public List<Version> getAllVersions() {
        return versionRepository.findAll();
    }

    public Version updateVersion(String id, String projectId, String versionName) {
        Optional<Version> optionalVersion = versionRepository.findById(id);
        if (optionalVersion.isPresent()) {
            Version version = optionalVersion.get();
            version.setProjectId(projectId);
            version.setVersionName(versionName);
            return versionRepository.save(version);
        }
        return null;
    }

    public void deleteVersion(String id) {
        versionRepository.deleteById(id);
    }
}
