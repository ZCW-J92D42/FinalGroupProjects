package com.peponapis.finalproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Document entity used to model all the information for the documents in the program.
 */
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id; // unique id for the document
    String title; // name/title of the document
    @Lob // specifies that this will be a Large Object
    @Column(length=394048542) // for some reason it was tinytext in the sql db, so specifying a really long length to get it to be a long text
    String body; // the text within the document

//    private byte[] body;
    Date creationDate; // date that document is created
    Date modificationDate; // date that document is modified

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    UserEntity userEntity; // user who created the document

    @OneToOne
    Folder folder; // folder the document is in

    /**
     *
     * TODO: ADD A METHOD/ENDPOINT FOR GETTING ALL DOCUMENTS CREATED BY A USER
     */


    public Document(){
        this.id = -1;
        this.creationDate = new Date();
        this.modificationDate = new Date();
    }

    public Document(String title, String body, int userId){
        this.title = title;
        this.body = body;
        // the dates will be automatically created upon
        // call to constructor
        this.creationDate = new Date();
        this.modificationDate = new Date();
        this.userEntity = userEntity;
    }

    public int getId() {
        return id;
    }

    // public void setId(int id) { this.id = id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    // public void setCreationDate(Date creationDate) {
//        this.creationDate = creationDate;
//    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

//    public Folder getFolder() {
//        return folder;
//    }
//
//    public void setFolder(Folder folder) {
//        this.folder = folder;
//    }
}
