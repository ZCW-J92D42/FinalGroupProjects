package com.peponapis.finalproject.service;

import com.peponapis.finalproject.dtos.DocumentDTO;
import com.peponapis.finalproject.dtos.UserDTO;
import com.peponapis.finalproject.model.Document;
import com.peponapis.finalproject.model.UserEntity;
import com.peponapis.finalproject.repository.DocumentRepo;
import com.peponapis.finalproject.repository.UserRepo;
import com.peponapis.finalproject.security.AuthenticationFacade;
import com.peponapis.finalproject.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.security.Principal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Document service. Operations relating to Document are found here.
 */
@Service
public class DocumentService {
    DocumentRepo documentRepo;
    UserRepo userRepo;
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public DocumentService(DocumentRepo documentRepo, AuthenticationFacade authenticationFacade, UserRepo userRepo){
        this.documentRepo = documentRepo;
        this.authenticationFacade = authenticationFacade;
        this.userRepo = userRepo;
    }

    /**
     *
     * @param document document to create
     * @return DTO of the new document
     */
    public DocumentDTO createDocument(Document document){
        Authentication authentication = authenticationFacade.getAuthentication();
        User user = (User) authentication.getPrincipal(); // getting authenticated user details

        // using above details (username) to find the user in our db
        UserEntity docAuthor = userRepo.findByUserName(user.getUsername()).get();

        this.documentRepo.save(document); // saving the document into the db first
        docAuthor.addDocument(document); // adding the document to user
        document.setUser(docAuthor); // adding the user to doc
        this.userRepo.save(docAuthor); // saving user with updated document list
        Document savedDoc = this.documentRepo.save(document); // saving document again, this time with user

        return new DocumentDTO(savedDoc);
    }

    /**
     *
     * @param document document to be updated
     * @return DTO of the updated document
     */
    public DocumentDTO updateDocument(DocumentDTO document){
        // get document by id
        Document doc = this.documentRepo.findById(document.getId()).get();

        // update doc's values with document's
        doc.setTitle(document.getTitle());
        doc.setBody(document.getBody());
        doc.setModificationDate(new Date());

        // save the document with updated values
        this.documentRepo.save(doc);

        // return updated doc as a response
        return new DocumentDTO(doc);
    }

    /**
     *
     * @return all documents within the db as a list
     */
    public List<DocumentDTO> getAllDocuments(){
        // gets all the Documents from the db, then uses stream to make them into dto & put them into a list
        return this.documentRepo.findAllByOrderByModificationDateDesc().stream().map(DocumentDTO::new).collect(Collectors.toList());
    }

    /**
     *
     * @param id id of the document
     * @return the document with the given id, or null
     */
    public DocumentDTO getDocument(int id){
        System.out.println("Retrieving document.....");
        if(this.documentRepo.findById(id).isPresent()) {
            return new DocumentDTO(this.documentRepo.findById(id).get());
        }
        return null;
    }

    /**
     *
     * @param filter search-term to filter documents by
     * @return list of documents containing that search-term
     */
    public List<DocumentDTO> searchDocuments(String filter){

        return this.documentRepo.searchDocuments(filter).stream().map(DocumentDTO:: new).collect(Collectors.toList());
    }

    public void deleteDocument(int id) {
        // get the document to be deleted from the db
        Document docToDelete = this.documentRepo.findById(id).get();
        // find the user that created it
        UserEntity author = docToDelete.getUser();
        // delete the document from the user's list of created docs
        // note: we do this first because doc has a foreign key to user...
        // have to get rid of the relationship between them first before deleting doc itself
        author.deleteDocument(docToDelete);
        // delete the doc from the db
        this.documentRepo.deleteById(id);
    }

    // Taking this out... DocRepo for explanation
//    public List<Document> getAllDocumentsByUser(int userId){
//        return this.documentRepo.getAllDocumentsByUserId(userId);
//    }
}
