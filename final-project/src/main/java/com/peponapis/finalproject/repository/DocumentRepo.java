package com.peponapis.finalproject.repository;

import com.peponapis.finalproject.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Integer> {

    // ?1 means first parameter

    /**
     * @param filter user-inputted search-term to filter documents by
     * @return a list of documents containing the search-term
     */
    @Query("SELECT d FROM Document d WHERE d.title LIKE %?1% OR d.body LIKE %?1%")
    List<Document> searchDocuments(String filter);
    // can possibly change this to findAllByTitleContaining(String filter)

    /**
     *
     * @return a list of documents ordered by most recently modified
     */
    List<Document> findAllByOrderByModificationDateDesc();

    List<Document> findAllByOrderByTitle();

    /**
     *
     * @param userId the id of the user
     * @return all documents created by that user
     *
     * THIS WILL PROBABLY BE IN USER, SINCE USER HAS
     * LIST OF DOCS... giving me grief because Document
     * model doesn't actually have userId, it has user.
     *
     */
//    @Query("SELECT d FROM Document d WHERE d.userId = ?1")
//    List<Document> getAllDocumentsByUserId(int userId);
}
