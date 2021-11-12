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
public class SearchFacet {

    private String documentAttributeKey;
    private String documentAttributeValueType;
    private List<FacetAttribute> facetAttributes;

}