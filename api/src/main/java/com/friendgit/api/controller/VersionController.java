package com.friendgit.api.controller;

import com.friendgit.api.entity.Version;
import com.friendgit.api.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/versions")
public class VersionController {
    @Autowired
    private VersionService versionService;

    @PostMapping
    public Version createVersion(@RequestParam String projectId, @RequestParam String versionName) {
        return versionService.createVersion(projectId, versionName);
    }

    // API để lấy tất cả các phiên bản
    @GetMapping
    public List<Version> getAllVersions() {
        return versionService.getAllVersions();
    }

    // API để lấy một phiên bản theo ID
/*    @GetMapping("/{id}")
    public Optional<Version> getVersionById(@PathVariable String id) {
        return versionService.getVersionById(id);
    }*/

    // API để cập nhật một phiên bản
    @PutMapping("/{id}")
    public Version updateVersion(@PathVariable String id, @RequestParam String projectId, @RequestParam String versionName) {
        return versionService.updateVersion(id, projectId, versionName);
    }

    // API để xóa một phiên bản
    @DeleteMapping("/{id}")
    public void deleteVersion(@PathVariable String id) {
        versionService.deleteVersion(id);
    }

}
