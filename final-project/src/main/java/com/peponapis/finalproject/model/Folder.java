package com.peponapis.finalproject.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

public class Folder {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int folderId;
    private String title;
    private Date creationDate;
    private Date modificationDate;
    @ManyToOne
    private UserEntity userEntity;
    @OneToMany
    private List<Document>docs;



    public Folder(String title){

        this.creationDate =  new Date();
        this.modificationDate = new Date();
        //this.userId = userId;
        this.title= title;
        this.docs = new ArrayList<>();
    }

    public Folder(){
         creationDate = new Date();
         modificationDate = new Date();
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

    public List<Document> getDocs() {
        return docs;
    }

    public void setDocs(List<Document> docs) {
        this.docs = docs;
    }
}
