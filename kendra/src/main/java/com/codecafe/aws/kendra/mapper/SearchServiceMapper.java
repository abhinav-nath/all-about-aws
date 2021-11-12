package com.codecafe.aws.kendra.mapper;

import com.codecafe.aws.kendra.domain.*;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.kendra.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SearchServiceMapper {

    public List<SearchResult> mapSearchResults(QueryResponse queryResponse) {
        List<SearchResult> searchResults = new ArrayList<>();

        for (QueryResultItem item : queryResponse.resultItems()) {
            if (QueryResultType.DOCUMENT.equals(item.type())) {
                List<SearchDocumentAttribute> searchDocumentAttributes = getSearchDocumentAttributes(item);

                SearchResult searchResult = SearchResult.builder()
                        .documentId(item.documentId())
                        .documentTitle(item.documentTitle().text())
                        .documentExcerpt(item.documentExcerpt().text())
                        .documentURI(item.documentURI())
                        .documentAttributes(searchDocumentAttributes)
                        .build();
                searchResults.add(searchResult);
            }
        }
        return searchResults;
    }

    private List<SearchDocumentAttribute> getSearchDocumentAttributes(QueryResultItem item) {
        List<SearchDocumentAttribute> searchDocumentAttributes = Collections.emptyList();

        if (item.hasDocumentAttributes()) {
            searchDocumentAttributes = new ArrayList<>();
            for (DocumentAttribute documentAttribute : item.documentAttributes()) {
                SearchDocumentAttributeValue searchDocumentAttributeValue = SearchDocumentAttributeValue.builder()
                        .stringValue(documentAttribute.value().stringValue())
                        .stringListValue(documentAttribute.value().stringListValue())
                        .longValue(documentAttribute.value().longValue())
                        .dateValue(documentAttribute.value().dateValue())
                        .build();

                SearchDocumentAttribute searchDocumentAttribute = SearchDocumentAttribute.builder()
                        .key(documentAttribute.key())
                        .value(searchDocumentAttributeValue)
                        .build();

                searchDocumentAttributes.add(searchDocumentAttribute);
            }
        }
        return searchDocumentAttributes;
    }

    public List<SearchFacet> mapSearchFacets(QueryResponse queryResponse) {
        List<SearchFacet> searchFacets = new ArrayList<>();

        for (FacetResult facetResult : queryResponse.facetResults()) {
            SearchFacet searchFacet = new SearchFacet();
            searchFacet.setDocumentAttributeKey(facetResult.documentAttributeKey());
            searchFacet.setDocumentAttributeValueType(facetResult.documentAttributeValueType().name());

            List<FacetAttribute> facetAttributes = new ArrayList<>();
            if (facetResult.hasDocumentAttributeValueCountPairs()) {
                for (DocumentAttributeValueCountPair documentAttributeValueCountPair : facetResult.documentAttributeValueCountPairs()) {
                    FacetAttribute facetAttribute = FacetAttribute.builder()
                            .documentAttributeValue(documentAttributeValueCountPair.documentAttributeValue().stringValue())
                            .count(documentAttributeValueCountPair.count())
                            .build();
                    facetAttributes.add(facetAttribute);
                }
                searchFacet.setFacetAttributes(facetAttributes);
            }

            searchFacets.add(searchFacet);
        }
        return searchFacets;
    }

}