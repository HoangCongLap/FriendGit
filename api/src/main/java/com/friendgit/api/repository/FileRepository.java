package com.friendgit.api.repository;

import com.friendgit.api.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<File, String> {
    boolean existsByProjectIdAndFileName(String projectId, String fileName);
}
