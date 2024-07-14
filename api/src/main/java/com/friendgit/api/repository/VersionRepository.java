package com.friendgit.api.repository;

import com.friendgit.api.entity.Version;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VersionRepository extends MongoRepository<Version, String> {
}
