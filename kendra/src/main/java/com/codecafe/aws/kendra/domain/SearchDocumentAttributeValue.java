package com.codecafe.aws.kendra.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDocumentAttributeValue {

    private String stringValue;
    private List<String> stringListValue;
    private Long longValue;
    private Instant dateValue;

}