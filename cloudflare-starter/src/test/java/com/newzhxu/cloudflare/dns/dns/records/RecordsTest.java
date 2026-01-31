package com.newzhxu.cloudflare.dns.dns.records;

import com.newzhxu.cloudflare.Cloudflare;
import com.newzhxu.cloudflare.CloudflareAutoConfiguartion;
import com.newzhxu.cloudflare.CloudflareR;
import com.newzhxu.cloudflare.dns.dns.records.Request.RecordRequest;
import com.newzhxu.cloudflare.dns.dns.records.result.ListRecordsR;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest(classes = CloudflareAutoConfiguartion.class)
class RecordsTest {
    @Autowired
    private Cloudflare cloudflare;
    private Records records;
    private String zoneId;
    String recordName = "test.newzhxu.com";

    Validator validator;


    @BeforeEach
    void setUp() {
        records = cloudflare.getDns().getDns().getRecords();
        this.zoneId = cloudflare.getZones().listZones().getResult().getFirst().getId();
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            this.validator = validatorFactory.getValidator();
        }

    }

    @Test
    void listRecords() {
        var listRecordsRCloudflareR = records.listRecords(zoneId);
        Assertions.assertTrue(listRecordsRCloudflareR.getSuccess());
    }

    @Test
    void dnsRecordDetails() {
        var listRecordsRCloudflareR = records.listRecords(zoneId);
        var recordId = listRecordsRCloudflareR.getResult().getFirst().getId();
        var recordDetails = records.dnsRecordDetails(zoneId, recordId);
        Assertions.assertTrue(recordDetails.getSuccess());

    }

    @Test
    void createDnsRecord() {
        RecordRequest recordRequest = new RecordRequest()
                .setType("A")
                .setContent("1.1.1.1")
                .setName(recordName);


        ;
        Set<ConstraintViolation<RecordRequest>> validate = validator.validate(recordRequest);
        if (!validate.isEmpty()) {
            for (ConstraintViolation<RecordRequest> recordRequestConstraintViolation : validate) {
                System.out.println(recordRequestConstraintViolation.getPropertyPath() + " " + recordRequestConstraintViolation.getMessage());
            }
            Assertions.fail("RecordRequest validation failed");
        }
        CloudflareR<ListRecordsR> dnsRecord = records.createDnsRecord(zoneId, recordRequest);
        Assertions.assertTrue(dnsRecord.getSuccess());
    }

    @Test
    void updateDnsRecord() {
        String recordId = records.listRecords(zoneId).getResult().stream().filter(record -> record.getName().equals(recordName)).map(ListRecordsR::getId).findFirst().orElseThrow();
        RecordRequest recordRequest = new RecordRequest()
                .setType("A")
                .setContent("1.1.1.2")
                .setName(recordName);
        Set<ConstraintViolation<RecordRequest>> validate = validator.validate(recordRequest);
        if (!validate.isEmpty()) {
            for (ConstraintViolation<RecordRequest> recordRequestConstraintViolation : validate) {
                System.out.println(recordRequestConstraintViolation.getPropertyPath() + " " + recordRequestConstraintViolation.getMessage());
            }
            Assertions.fail("RecordRequest validation failed");
        }
        CloudflareR<ListRecordsR> dnsRecord = records.updateDnsRecord(zoneId, recordId, recordRequest);
        Assertions.assertTrue(dnsRecord.getSuccess());
    }

    @Test
    void patchDnsRecord() {
        String recordId = records.listRecords(zoneId).getResult().stream().filter(record -> record.getName().equals(recordName)).map(ListRecordsR::getId).findFirst().orElseThrow();
        RecordRequest recordRequest = new RecordRequest()
                .setType("A")
                .setContent("1.1.1.2")
                .setName(recordName);
        Set<ConstraintViolation<RecordRequest>> validate = validator.validate(recordRequest);
        if (!validate.isEmpty()) {
            for (ConstraintViolation<RecordRequest> recordRequestConstraintViolation : validate) {
                System.out.println(recordRequestConstraintViolation.getPropertyPath() + " " + recordRequestConstraintViolation.getMessage());
            }
            Assertions.fail("RecordRequest validation failed");
        }
        CloudflareR<ListRecordsR> dnsRecord = records.updateDnsRecord(zoneId, recordId, recordRequest);
        Assertions.assertTrue(dnsRecord.getSuccess());

    }

    @Test
    void deleteDnsRecord() {
        String recordId = records.listRecords(zoneId).getResult().stream().filter(record -> record.getName().equals(recordName)).map(ListRecordsR::getId).findFirst().orElseThrow();

        var listRecordsRCloudflareR = records.deleteDnsRecord(zoneId, recordId);
        Assertions.assertTrue(listRecordsRCloudflareR.getSuccess());
    }

    @Test
    void exportDnsRecords() {
        var exportDnsRecords = records.exportDnsRecords(zoneId);
        System.out.println(exportDnsRecords);
    }
}