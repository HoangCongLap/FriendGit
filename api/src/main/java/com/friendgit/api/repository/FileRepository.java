package com.friendgit.api.repository;

import com.friendgit.api.entity.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends MongoRepository<FileEntity, String> {
    boolean existsByProjectIdAndFileName(String projectId, String fileName) throws IOException;

    @Query(value = "{ 'project_id': ?0, 'file_name': ?1 }", fields = "{ 'file_path': 1 }")
    Optional<String> findFilePathByProjectIdAndFileName(String projectId, String fileName);

    List<FileEntity> findByProjectId(String projectId);
}
