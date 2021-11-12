package com.codecafe.aws.kendra.service;

import com.codecafe.aws.kendra.config.AWSConfig;
import com.codecafe.aws.kendra.domain.DataSyncRequest;
import com.codecafe.aws.kendra.domain.DataSyncResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.kendra.model.StartDataSourceSyncJobRequest;
import software.amazon.awssdk.services.kendra.model.StartDataSourceSyncJobResponse;

import java.util.Optional;

@Service
public class DataSyncService {

    private final AWSConfig awsConfig;

    @Autowired
    public DataSyncService(AWSConfig awsConfig) {
        this.awsConfig = awsConfig;
    }

    public Optional<DataSyncResponse> startDataSync(DataSyncRequest dataSyncRequest) {
        StartDataSourceSyncJobResponse response = awsConfig.kendraClient()
                .startDataSourceSyncJob(StartDataSourceSyncJobRequest.builder()
                        .id(dataSyncRequest.getId())
                        .indexId(dataSyncRequest.getIndexId())
                        .build());

        if (response == null)
            return Optional.empty();

        return Optional.of(DataSyncResponse.builder().executionId(response.executionId()).build());
    }

}