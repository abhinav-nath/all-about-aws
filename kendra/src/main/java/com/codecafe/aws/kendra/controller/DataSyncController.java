package com.codecafe.aws.kendra.controller;

import com.codecafe.aws.kendra.domain.DataSyncRequest;
import com.codecafe.aws.kendra.domain.DataSyncResponse;
import com.codecafe.aws.kendra.service.DataSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/datasync")
public class DataSyncController {

    private final DataSyncService dataSyncService;

    @Autowired
    public DataSyncController(DataSyncService dataSyncService) {
        this.dataSyncService = dataSyncService;
    }

    @PostMapping("/start")
    public ResponseEntity<DataSyncResponse> startDataSourceSync(@RequestBody DataSyncRequest dataSyncRequest) {
        Optional<DataSyncResponse> dataSyncResponseOptional = dataSyncService.startDataSync(dataSyncRequest);
        return ResponseEntity.ok().body(dataSyncResponseOptional.get());
    }

}