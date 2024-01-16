package com.peponapis.finalproject.service;

import com.peponapis.finalproject.model.Document;
import com.peponapis.finalproject.model.Folder;
import com.peponapis.finalproject.repository.FolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {

    FolderRepo folderRepo;
    @Autowired
     public FolderService(FolderRepo folderRepo){
         this.folderRepo = folderRepo;
     }


    public List<Document> getDocsInFolder(int folderId){
       return folderRepo.findById(folderId).get().getDocs();
    }
    public Folder saveFolder(Folder folder){
      return folderRepo.save(folder);
    }

    public void deleteFolder(int FolderId){
         folderRepo.deleteById(FolderId);
    }

    public Folder updateFolder(Folder folder){
         return folderRepo.save(folder);
    }
}
