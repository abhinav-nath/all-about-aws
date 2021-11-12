package com.codecafe.aws.kendra.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {

    private String documentId;
    private String documentTitle;
    private String documentExcerpt;
    private String documentURI;
    private List<SearchDocumentAttribute> documentAttributes;

}