package com.peponapis.finalproject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * User entity used to model all the information for users in the program.
 */

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId; // unique id for users
    private String name; // name for the user
    private String userName; // username for the user (login info)
    private String password; // password for the user (login info)

    @OneToMany
    private List<Document> documents;

    @OneToMany
    private List<Folder> folders;

    public UserEntity() {
    }

    public UserEntity(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public void addDocument(Document doc){this.documents.add(doc);}

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public void deleteDocument(Document document){
        this.documents.remove(document);
    }
}
