package com.peponapis.finalproject.modelTests;

import com.peponapis.finalproject.model.Document;
import com.peponapis.finalproject.model.Folder;
import com.peponapis.finalproject.model.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserEntityTests {

    @Test
    public void getAndSetUserId() {

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(1);

        Integer expected = 1;

        Assertions.assertEquals(expected, userEntity.getUserId());
    }
    @Test
    public void getAndSetName() {

        UserEntity userEntity = new UserEntity();
        userEntity.setName("Bob");

        String expected = "Bob";

        Assertions.assertEquals(expected, userEntity.getName());
    }
    @Test
    public void getAndSetUserName() {

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("username");

        String expected = "username";

        Assertions.assertEquals(expected, userEntity.getUserName());
    }
    @Test
    public void getAndSetPassword() {

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("FacebookStoresItInATextFile");

        String expected = "FacebookStoresItInATextFile";

        Assertions.assertEquals(expected, userEntity.getPassword());
    }
    @Test
    public void getAndSetUserDocuments() {

        UserEntity userEntity = new UserEntity();

        Document document = new Document();

        // How to set up test for getting document.
    }
    @Test
    public void getAndSetUserFolders() {

        UserEntity userEntity = new UserEntity();

        Folder folder = new Folder();

        //How to set up test for getting folders and also place documents in.
    }

}
