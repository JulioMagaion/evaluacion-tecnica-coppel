package com.tvmaze.api.midd.repositories;



import com.tvmaze.api.midd.models.ShowDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends MongoRepository<ShowDocument, Long> {
}