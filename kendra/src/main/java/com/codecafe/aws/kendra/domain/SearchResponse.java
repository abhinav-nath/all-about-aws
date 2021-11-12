package com.codecafe.aws.kendra.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {

    private int totalNumberOfResults;
    private List<SearchResult> searchResults;
    private List<SearchFacet> facets;

}