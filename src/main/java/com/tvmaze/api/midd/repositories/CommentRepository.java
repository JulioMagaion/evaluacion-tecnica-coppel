package com.tvmaze.api.midd.repositories;


import com.tvmaze.api.midd.models.CommentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<CommentDocument, String> {
    List<CommentDocument> findByShowId(Long showId);
}