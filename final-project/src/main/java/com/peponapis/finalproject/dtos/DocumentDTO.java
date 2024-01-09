package com.peponapis.finalproject.dtos;


import com.peponapis.finalproject.model.Document;

import java.util.Date;

public class DocumentDTO {
    private int id;
    private String title;
    private String body;
    private Date creationDate;
    private Date modificationDate;
    private String author;

    public DocumentDTO(int id, String title, String body, Date creationDate, Date modificationDate, String author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.author = author;
    }

    public DocumentDTO(Document document){
        this.id = document.getId();
        this.title = document.getTitle();
        this.body = document.getBody();
        this.creationDate = document.getCreationDate();
        this.modificationDate = document.getModificationDate();
        this.author = document.getUser().getName();
    }

    public DocumentDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
