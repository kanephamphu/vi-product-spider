package com.vicoupon.auto_crawler.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.vicoupon.auto_crawler.model.ProductPrice;
import com.vicoupon.auto_crawler.service.CsvExportService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
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
    private final String bucketName = "your-s3-bucket-name"; // Replace with your bucket name

    public CsvExportServiceImpl() {
        this.s3Client = AmazonS3ClientBuilder.defaultClient();
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
