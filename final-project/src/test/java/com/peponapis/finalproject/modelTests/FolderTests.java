package com.peponapis.finalproject.modelTests;

import com.peponapis.finalproject.model.Folder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class FolderTests {

  @Test
    public void testFolderNullConstruction(){
    Folder folder = new Folder();
    Assertions.assertNotNull(folder);
  }

  @Test
  public void testFolderConstructor(){
     String title = "title";
     int userId = 1;

    Folder folder = new Folder();

    Assertions.assertNotNull(folder);
  }


  @Test
  public void testSetFolderId(){
     Folder folder = new Folder();
     int folderId = 1;

    folder.setFolderId(folderId);
    int FolderId = folder.getFolderId();

   Assertions.assertEquals(folderId, FolderId);
  }

  @Test
  public void testGetFolderId(){
    Folder folder = new Folder();
    int folderId = 1;

    folder.setFolderId(folderId);
    Integer FolderId = folder.getFolderId();

    Assertions.assertEquals(folderId, FolderId);
  }


  @Test
  public void testFolderGetTitle() {
    Folder folder = new Folder();
    String title = "title";

    folder.setTitle(title);
    String actualTitle = folder.getTitle();

    Assertions.assertEquals(title, actualTitle);
  }


  @Test
  public void testFolderSetTitle(){
    String title = "title";
    Folder folder = new Folder();

    folder.setTitle(title);

    Assertions.assertEquals(title, folder.getTitle());

  }



  @Test
  public void testFolderSetCreationDate(){
    Folder folder = new Folder();
    Date date = new Date();

    folder.setCreationDate(date);
    Assertions.assertEquals(date, folder.getCreationDate());
  }

  @Test
  public void testFolderGetCreationDate(){
     Folder folder = new Folder();

     Assertions.assertNotNull(folder.getCreationDate());
  }

  @Test
  public void testFolderGetModification(){
   Folder folder = new Folder();
   Assertions.assertNotNull(folder.getModificationDate());

  }

  @Test
  public void testFolderSetModification(){

    Folder folder = new Folder();
    Date date = new Date();

    folder.setModificationDate(date);
    Assertions.assertEquals(date, folder.getModificationDate());

  }

  @Test
  public void testFolderGetUserId() {
    Folder folder = new Folder();
  }

  @Test
  public void testFolderSetUserId(){



  }

  @Test
  public void  testFolderGetDocs(){


  }

  @Test
  public void testFolderSetDocs(){

  }
}
