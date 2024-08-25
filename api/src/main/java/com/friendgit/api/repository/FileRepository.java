package com.friendgit.api.repository;

import com.friendgit.api.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends MongoRepository<File, String> {
    Optional<File> findByFileNameAndCreatedByUserId(String fileId, String createdByUserId);
}
