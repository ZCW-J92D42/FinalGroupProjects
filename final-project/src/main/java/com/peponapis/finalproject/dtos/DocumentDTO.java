package com.peponapis.finalproject.dtos;


import com.peponapis.finalproject.model.Document;

import java.util.Date;

/**
 * Data Transfer Object for Document. Used for passing only the relevant document info.
 */
public class DocumentDTO {
    private int id;
    private String title;
    private String body;
    private Date creationDate;
    private Date modificationDate;
    private int author_id;
    private String author;

    // TODO: if there's no uses for this later, take it out
    public DocumentDTO(int id, String title, String body, Date creationDate, Date modificationDate, int author_id, String author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.author_id = author_id;
        this.author = author;
    }


    // constructor to make dto out of Document
    public DocumentDTO(Document document){
        this.id = document.getId();
        this.title = document.getTitle();
        this.body = document.getBody();
        this.creationDate = document.getCreationDate();
        this.modificationDate = document.getModificationDate();
        this.author_id = document.getUser().getUserId();
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

    public int getAuthorId() {
        return author_id;
    }

    public void setAuthorId(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
