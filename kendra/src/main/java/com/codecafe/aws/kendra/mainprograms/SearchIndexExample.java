package com.codecafe.aws.kendra.mainprograms;

import software.amazon.awssdk.services.kendra.KendraClient;
import software.amazon.awssdk.services.kendra.model.*;

public class SearchIndexExample {

    public static void main(String[] args) {
        KendraClient kendra = KendraConfig.kendraClient();

        String query = "product";

        QueryRequest queryRequest = QueryRequest
                .builder()
                .queryText(query)
                .indexId(KendraConfig.getSearchIndexId())
                .build();

        QueryResponse queryResponse = kendra.query(queryRequest);

        System.out.println(String.format("Search results for query: %s", query));

        for (QueryResultItem item : queryResponse.resultItems()) {
            System.out.println("----------------------");
            System.out.println(String.format("Type: %s", item.type()));
            switch (item.type()) {
                case QUESTION_ANSWER:
                case ANSWER:
                    String answerText = item.documentExcerpt().text();
                    System.out.println(answerText);
                    break;
                case DOCUMENT:
                    String documentTitle = item.documentTitle().text();
                    System.out.println(String.format("Title: %s", documentTitle));
                    String documentExcerpt = item.documentExcerpt().text();
                    System.out.println(String.format("Excerpt: %s", documentExcerpt));
                    break;
                default:
                    System.out.println(String.format("Unknown query result type: %s", item.type()));
            }
            System.out.println("-----------------------\n");
        }

        if (queryResponse.hasFacetResults()) {
            System.out.println("**** FACETS ****");
            for (FacetResult facetResult : queryResponse.facetResults()) {
                System.out.println("-----------------------\n");
                System.out.println(String.format("Document Attribute Key: %s", facetResult.documentAttributeKey()));
                System.out.println(String.format("Document Attribute Value Type: %s", facetResult.documentAttributeValueType()));

                if (facetResult.hasDocumentAttributeValueCountPairs()) {
                    for (DocumentAttributeValueCountPair documentAttributeValueCountPair : facetResult.documentAttributeValueCountPairs()) {
                        System.out.println(documentAttributeValueCountPair.documentAttributeValue().stringValue() + " : " +
                                documentAttributeValueCountPair.count());
                    }
                }
            }
        }
    }

}