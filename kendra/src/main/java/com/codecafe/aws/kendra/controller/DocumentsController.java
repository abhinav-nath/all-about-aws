package com.codecafe.aws.kendra.controller;

import com.codecafe.aws.kendra.domain.AddDocumentRequest;
import com.codecafe.aws.kendra.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.kendra.model.BatchDeleteDocumentResponse;
import software.amazon.awssdk.services.kendra.model.BatchPutDocumentResponse;

import java.util.Optional;

@RestController
@RequestMapping("/documents")
public class DocumentsController {

    private final DocumentsService documentsService;

    @Autowired
    public DocumentsController(DocumentsService documentsService) {
        this.documentsService = documentsService;
    }

    @PostMapping
    public ResponseEntity<Void> addDocument(@RequestBody AddDocumentRequest addDocumentRequest) {
        Optional<BatchPutDocumentResponse> addDocumentResponseOptional = documentsService.addNewDocument(addDocumentRequest);

        if (addDocumentResponseOptional.isEmpty())
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String documentId) {
        Optional<BatchDeleteDocumentResponse> deleteDocumentResponseOptional = documentsService.deleteDocument(documentId);

        if (deleteDocumentResponseOptional.isEmpty())
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
