package com.friendgit.api.repository;

import com.friendgit.api.model.Repo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepoRepository extends MongoRepository<Repo, String> {

}
