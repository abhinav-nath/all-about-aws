package com.codecafe.aws.kendra.mainprograms;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kendra.KendraClient;
import software.amazon.awssdk.services.kendra.model.BatchPutDocumentRequest;
import software.amazon.awssdk.services.kendra.model.BatchPutDocumentResponse;
import software.amazon.awssdk.services.kendra.model.ContentType;
import software.amazon.awssdk.services.kendra.model.Document;

public class AddDocumentsViaAPIExample {

    public static void main(String[] args) {
        KendraClient kendra = KendraConfig.kendraClient();

        Document testDoc = Document
                .builder()
                .title("The title of your document")
                .id("a_doc_id")
                .blob(SdkBytes.fromUtf8String("your text content"))
                .contentType(ContentType.PLAIN_TEXT)
                .build();

        BatchPutDocumentRequest batchPutDocumentRequest = BatchPutDocumentRequest
                .builder()
                .indexId(KendraConfig.getSearchIndexId())
                .documents(testDoc)
                .build();

        BatchPutDocumentResponse result = kendra.batchPutDocument(batchPutDocumentRequest);

        System.out.println(String.format("BatchPutDocument Result: %s", result));
    }

}