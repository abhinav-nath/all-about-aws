package com.codecafe.aws.kendra.service;

import com.codecafe.aws.kendra.config.AWSConfig;
import com.codecafe.aws.kendra.domain.AddDocumentRequest;
import com.codecafe.aws.kendra.mainprograms.KendraConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kendra.model.*;

import java.util.Optional;

@Service
public class DocumentsService {

    private final AWSConfig awsConfig;

    @Autowired
    public DocumentsService(AWSConfig awsConfig) {
        this.awsConfig = awsConfig;
    }

    public Optional<BatchPutDocumentResponse> addNewDocument(AddDocumentRequest addDocumentRequest) {
        Document document = Document
                .builder()
                .title(addDocumentRequest.getTitle())
                .id(addDocumentRequest.getId())
                .blob(SdkBytes.fromUtf8String(addDocumentRequest.getTextContent()))
                .contentType(ContentType.PLAIN_TEXT)
                .build();

        BatchPutDocumentRequest batchPutDocumentRequest = BatchPutDocumentRequest
                .builder()
                .indexId(awsConfig.getSearchIndexId())
                .documents(document)
                .build();

        BatchPutDocumentResponse result = awsConfig.kendraClient().batchPutDocument(batchPutDocumentRequest);

        if (result == null)
            return Optional.empty();

        return Optional.of(result);
    }

    public Optional<BatchDeleteDocumentResponse> deleteDocument(String documentId) {
        BatchDeleteDocumentRequest batchDeleteDocumentRequest = BatchDeleteDocumentRequest
                .builder()
                .documentIdList(documentId)
                .indexId(KendraConfig.getSearchIndexId())
                .build();

        BatchDeleteDocumentResponse result = awsConfig.kendraClient().batchDeleteDocument(batchDeleteDocumentRequest);

        if (result == null)
            return Optional.empty();

        return Optional.of(result);
    }

}