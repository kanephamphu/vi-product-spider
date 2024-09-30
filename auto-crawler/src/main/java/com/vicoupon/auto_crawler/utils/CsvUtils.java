package com.vicoupon.auto_crawler.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;

public class CsvUtils {
    public static List<CSVRecord> getCSVRecords(String path) {
        List<CSVRecord> csvRecords = new ArrayList<>();

        try (Reader reader = new FileReader(path)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);

            for (CSVRecord record : records) {
                csvRecords.add(record);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvRecords;
    }
}
