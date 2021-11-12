package com.codecafe.aws.kendra.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import software.amazon.awssdk.services.kendra.KendraClient;

@Configuration
public class AWSConfig {

    private final AWSProperties awsProperties;

    @Autowired
    public AWSConfig(AWSProperties awsProperties) {
        this.awsProperties = awsProperties;
    }

    @Bean
    public KendraClient kendraClient() {
        System.setProperty("aws.accessKeyId", awsProperties.getAccessKeyId());
        System.setProperty("aws.secretAccessKey", awsProperties.getSecretAccessKey());
        System.setProperty("aws.sessionToken", awsProperties.getSessionToken());

        return KendraClient.builder()
                .region(software.amazon.awssdk.regions.Region.US_EAST_1)
                .build();
    }

    public String getSearchIndexId() {
        return awsProperties.getSearchIndexId();
    }

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder mapperBuilder) {
        return mapperBuilder.build().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

}


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "aws")
@Getter
@Setter
class AWSProperties {

    private String accessKeyId;
    private String secretAccessKey;
    private String sessionToken;
    private String searchIndexId;

}