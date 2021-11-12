package com.codecafe.aws.kendra.service;

import com.codecafe.aws.kendra.config.AWSConfig;
import com.codecafe.aws.kendra.domain.SearchFacet;
import com.codecafe.aws.kendra.domain.SearchResponse;
import com.codecafe.aws.kendra.domain.SearchResult;
import com.codecafe.aws.kendra.mapper.SearchServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.kendra.model.QueryRequest;
import software.amazon.awssdk.services.kendra.model.QueryResponse;

import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    private final AWSConfig awsConfig;
    private SearchServiceMapper mapper;

    private SearchResponse searchResponse;

    @Autowired
    public SearchService(AWSConfig awsConfig, SearchServiceMapper mapper) {
        this.awsConfig = awsConfig;
        this.mapper = mapper;
        this.searchResponse = new SearchResponse();
    }

    public Optional<SearchResponse> searchText(String text) {
        QueryRequest queryRequest = QueryRequest
                .builder()
                .queryText(text)
                .indexId(awsConfig.getSearchIndexId())
                .build();

        QueryResponse queryResponse = awsConfig.kendraClient().query(queryRequest);

        if (queryResponse.hasResultItems()) {
            List<SearchResult> searchResults = mapper.mapSearchResults(queryResponse);
            searchResponse.setTotalNumberOfResults(queryResponse.totalNumberOfResults());
            searchResponse.setSearchResults(searchResults);
        } else {
            return Optional.empty();
        }

        if (queryResponse.hasFacetResults()) {
            List<SearchFacet> searchFacets = mapper.mapSearchFacets(queryResponse);
            searchResponse.setFacets(searchFacets);
        }

        return Optional.of(searchResponse);
    }

}