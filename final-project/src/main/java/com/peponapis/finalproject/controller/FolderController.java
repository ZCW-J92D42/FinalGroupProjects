package com.peponapis.finalproject.controller;

import com.peponapis.finalproject.model.Document;
import com.peponapis.finalproject.model.Folder;
import com.peponapis.finalproject.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Folder")
public class FolderController {

    FolderService folderService;
@Autowired
    public FolderController (FolderService folderService){
          this.folderService = folderService;
    }


@GetMapping("/view{Id}")
    public List<Document> viewDocs(@PathVariable("Id") int folderId){
       return this.folderService.getDocsInFolder(folderId);
    }
@GetMapping("/create")
    public Folder createFolder(@RequestBody Folder folder){
      return this.folderService.saveFolder(folder) ;
    }
@GetMapping("/delete{Id}")
    public void deleteFolder(@PathVariable("Id") int folderId){
      folderService.deleteFolder(folderId);
    }

@GetMapping("/update")
    public Folder updateFolder(@RequestBody Folder folder){
     return folderService.saveFolder(folder);
    }
}
