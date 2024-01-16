package com.peponapis.finalproject.modelTests;

import com.peponapis.finalproject.model.Document;
import com.peponapis.finalproject.model.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DocumentTests {

    @Test
    public void testDocumentNullConstructor(){
        Document document = new Document();

        Assertions.assertNotNull(document);
    }

    @Test
    public void testDocumentConstructor(){
        String title = "title";
        String body = "body";
        int userId = 1;
        Document document = new Document(title, body, userId);

        Assertions.assertNotNull(document);
    }

    @Test
    public void testDocumentGetId(){
        Document document = new Document();
        Assertions.assertEquals(-1, document.getId());
    }
    @Test
    public void testDocumentSetTitle(){
        String title = "title";
        Document document = new Document();

        document.setTitle(title);

        Assertions.assertEquals(title, document.getTitle());
    }

    @Test
    public void testDocumentSetBody(){
        String body = "this is a body";
        Document document = new Document();

        document.setBody(body);

        Assertions.assertEquals(body, document.getBody());
    }

    @Test
    public void testDocumentSetModificationDate(){
        Document document = new Document();
        Date date = new Date();

        document.setModificationDate(date);

        Assertions.assertEquals(date, document.getModificationDate());
    }

    @Test
    public void testDocumentGetUser(){
        UserEntity userEntity = new UserEntity();
        Document document = new Document();

        document.setUser(userEntity);

        Assertions.assertEquals(userEntity, document.getUser());
    }

    @Test
    public void testDocumentGetCreationDate(){
        Document document = new Document();

        Assertions.assertNotNull(document.getCreationDate());
    }

}
