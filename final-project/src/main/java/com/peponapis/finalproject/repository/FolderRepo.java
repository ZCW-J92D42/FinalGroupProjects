package com.peponapis.finalproject.repository;

import com.peponapis.finalproject.model.Document;
import com.peponapis.finalproject.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface FolderRepo extends JpaRepository<Folder,Integer> {


}
