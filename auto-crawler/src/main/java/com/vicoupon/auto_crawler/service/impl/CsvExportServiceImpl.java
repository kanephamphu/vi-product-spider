package com.vicoupon.auto_crawler.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.vicoupon.auto_crawler.config.AmazonConfiguration;
import com.vicoupon.auto_crawler.model.ProductPrice;
import com.vicoupon.auto_crawler.service.CsvExportService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CsvExportServiceImpl implements CsvExportService {
    private final AmazonS3 s3Client;
    private final String bucketName = "vicoupon"; // Replace with your bucket name

    public CsvExportServiceImpl(AmazonConfiguration amazonConfiguration) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(amazonConfiguration.getAccessKey(), amazonConfiguration.getSecretKey());

        this.s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(amazonConfiguration.getRegion())
                .build();
    }

    @Override
    public void exportProducePrices(List<ProductPrice> productPrices, String fileName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("id", "price", "url", "provider", "system_id"))) {

            for (ProductPrice price : productPrices) {
                csvPrinter.printRecord(
                        price.getId(),
                        price.getPrice(),
                        price.getUrl(),
                        price.getCrawlerProvider(),
                        price.getSystemId()
                        );
            }
            csvPrinter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Upload to S3
        byte[] csvData = outputStream.toByteArray();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(csvData.length);
        metadata.setContentType("text/csv");


        s3Client.putObject(bucketName, "data_sources/" + fileName, new ByteArrayInputStream(csvData), metadata);
    }
}
