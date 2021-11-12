package com.codecafe.aws.kendra.mainprograms;

import software.amazon.awssdk.services.kendra.KendraClient;

import java.io.FileReader;
import java.util.Properties;

public class KendraConfig {

    private static String searchIndexId;

    static {
        try {
            FileReader reader = new FileReader("src/main/resources/application.properties");
            Properties p = new Properties();
            p.load(reader);
            searchIndexId = p.getProperty("aws.search-index-id");
            System.setProperty("aws.accessKeyId", p.getProperty("aws.access-key-id"));
            System.setProperty("aws.secretAccessKey", p.getProperty("aws.secret-access-key"));
            System.setProperty("aws.sessionToken", p.getProperty("aws.session-token"));
        } catch (Exception e) {
            System.out.println("Error in resolving properties");
            System.exit(1);
        }
    }

    public static KendraClient kendraClient() {
        return KendraClient.builder()
                .region(software.amazon.awssdk.regions.Region.US_EAST_1)
                .build();
    }

    public static String getSearchIndexId() {
        return searchIndexId;
    }

}