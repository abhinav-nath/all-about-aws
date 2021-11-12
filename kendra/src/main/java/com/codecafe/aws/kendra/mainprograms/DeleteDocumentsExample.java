package com.codecafe.aws.kendra.mainprograms;

import software.amazon.awssdk.services.kendra.KendraClient;
import software.amazon.awssdk.services.kendra.model.BatchDeleteDocumentRequest;
import software.amazon.awssdk.services.kendra.model.BatchDeleteDocumentResponse;

public class DeleteDocumentsExample {

    public static void main(String[] args) {
        KendraClient kendra = KendraConfig.kendraClient();

        BatchDeleteDocumentRequest batchDeleteDocumentRequest = BatchDeleteDocumentRequest
                .builder()
                .documentIdList("a_doc_id")
                .indexId(KendraConfig.getSearchIndexId())
                .build();

        BatchDeleteDocumentResponse result = kendra.batchDeleteDocument(batchDeleteDocumentRequest);

        System.out.println(String.format("BatchDeleteDocument Result: %s", result));
    }

}