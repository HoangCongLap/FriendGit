package com.friendgit.api.controller;

import com.friendgit.api.model.Repo;
import com.friendgit.api.repository.RepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
public class RepositoryController {

    @Autowired
    private RepoRepository repoRepository;

    @GetMapping("/repositories/{id}")
    public ResponseEntity<?> getSingleRepository(@PathVariable String id){
        Optional<Repo> repo = repoRepository.findById(id);
        if(repo.isPresent()){
            return new ResponseEntity<>(repo, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Repository not found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/repositories")
    public ResponseEntity<?> getAllRepository(){
        List<Repo> repos = repoRepository.findAll();
        if(repos.isEmpty()){
            return new ResponseEntity<>("No token", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(repos,HttpStatus.OK);
        }
    }

    @PostMapping("/repositories")
    public ResponseEntity<?> createRepository(@RequestBody Repo repo){
        try{
            repo.setCreated_time(new Date(System.currentTimeMillis()));
            repo.setUpdated_time(new Date(System.currentTimeMillis()));
            repoRepository.save(repo);
            return new ResponseEntity<>(repo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping("/repositories/{id}")
    public ResponseEntity<?> updateRepository(@PathVariable("id") String id, @RequestBody Repo repo){
        Optional<Repo> repoOptional = repoRepository.findById(id);
        if(repoOptional.isPresent()){
            repo.setUpdated_time(new Date(System.currentTimeMillis()));
            repoRepository.save(repo);
            return new ResponseEntity<>(repo, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Repo can not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/repositories/{id}")
    public ResponseEntity<?> deleteRepository(@PathVariable("id") String id){

        try{
            repoRepository.deleteById(id);
            return new ResponseEntity<>("Successfully to delete by id: "+ id, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}