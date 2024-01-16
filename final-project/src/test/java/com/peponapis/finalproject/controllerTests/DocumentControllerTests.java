package com.peponapis.finalproject.controllerTests;

import com.peponapis.finalproject.controller.DocumentController;
import com.peponapis.finalproject.dtos.DocumentDTO;
import com.peponapis.finalproject.model.Document;
import com.peponapis.finalproject.model.UserEntity;
import com.peponapis.finalproject.security.AuthenticationFacade;
import com.peponapis.finalproject.service.DocumentService;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.Doc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = DocumentController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class DocumentControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    DocumentService documentService;

    DocumentController documentController;

    @BeforeEach
    public void setUp(){
        this.documentController = new DocumentController(this.documentService);
    }

    @Test
    public void testDocumentControllerConstructor(){
        Assertions.assertThat(this.documentController).isNotNull();
    }

    @Test
    public void testDocumentControllerViewAllDocuments() throws Exception {
        // given
        DocumentDTO dto1 = Mockito.mock(DocumentDTO.class);
        Document doc1 = Mockito.mock(Document.class);
        List<DocumentDTO> list = new ArrayList<>();
        list.add(dto1);
        UserEntity user = Mockito.mock(UserEntity.class);

        // when
        when(this.documentService.getAllDocuments()).thenReturn(list);
        when(doc1.getUser()).thenReturn(user);
        when(user.getUserId()).thenReturn(1);


        // assert
        this.mockMvc.perform(get("/document/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testDocumentControllerViewDocument() throws Exception {
        // given
        int id = 2;
        UserEntity user = Mockito.mock(UserEntity.class);
        Document document = Mockito.mock(Document.class);
        document.setUser(user);
        DocumentDTO dto = Mockito.mock(DocumentDTO.class);
        // when
        when(this.documentService.getDocument(id)).thenReturn(dto);
        when(document.getUser()).thenReturn(user);

        // assert
        this.mockMvc.perform(get("/document/view/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testDocumentControllerSaveDocument() throws Exception {
        // given
        Document document = Mockito.mock(Document.class);
        DocumentDTO dto = Mockito.mock(DocumentDTO.class);
        String requestBody = "{\"id\":1, \"title\":\"Title1\", \"body\":\"Body1\", \"userId\":123}";
        UserEntity user = Mockito.mock(UserEntity.class);

        // when
        when(this.documentService.updateDocument(dto)).thenReturn(dto);
        when(document.getUser()).thenReturn(user);
        when(user.getUserId()).thenReturn(1);

        // assert
        this.mockMvc.perform(put("/document/update")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk());

    }

    @Test
    public void testDocumentControllerCreateDocument() throws Exception {
        // given
        Document document = Mockito.mock(Document.class);
        DocumentDTO dto = Mockito.mock(DocumentDTO.class);
        String requestBody = "{\"id\":1, \"title\":\"Title1\", \"body\":\"Body1\", \"userId\":123}";
        // when
        when(this.documentService.createDocument(document)).thenReturn(dto);

        // assert
        this.mockMvc.perform(post("/document/create")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void testDocumentControllerSearchDocument() throws Exception {
        // given
        DocumentDTO dto1 = Mockito.mock(DocumentDTO.class);
        String filter = "test";
        List<DocumentDTO> dtoList = Mockito.anyList();
        dtoList.add(dto1);

        //when
        when(this.documentService.searchDocuments(filter)).thenReturn(dtoList);

        // assert
        this.mockMvc.perform(get("/document/search")
                        .param("filter", filter)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

//    @Test
//    public void testDocumentControllerGetAllByUser() throws Exception {
//        // given
//        List<Document> documentList = Arrays.asList(new Document("Test1", "Body1", 123), new Document("Test2", "Body2", 123));
//        int userId = 123;
//
//        // when
//        when(this.documentService.getAllDocumentsByUser(userId)).thenReturn(documentList);
//
//        // assert
//        this.mockMvc.perform(get("/document/allByUser")
//                .param("userId", String.valueOf(userId)))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//    }

}
