package com.codecafe.aws.kendra.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacetAttribute {

    private String documentAttributeValue;
    private int count;

}