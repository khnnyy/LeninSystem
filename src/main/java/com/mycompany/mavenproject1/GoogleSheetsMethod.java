package com.mycompany.mavenproject1;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GoogleSheetsMethod {

     //Replace with your actual client ID and client secret
    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    // The ID of the spreadsheet to update
    private static final String SPREADSHEET_ID = "";

    public static void updateGoogleSheet(String jobOrderType, String clientName, String address, String contact, String concern, String leader, String transportation, String jobCode) {
        try {
            // Initialize Sheets service
            Sheets sheetsService = GoogleSheetsIntegration.getSheetsService(CLIENT_ID, CLIENT_SECRET);

            // Get the next job code
            String nextJobCode = getNextJobCode(sheetsService, jobOrderType);

            // Update job code in UI
            jobCode = nextJobCode;

            // Specify the range to append data (appending to the end of the sheet)
            String range = "Sheet1!A:H"; // Adjust the range as per your sheet's structure

            // Create some data to insert
            List<List<Object>> data = Collections.singletonList(
                    Arrays.asList(jobOrderType, clientName, address, contact, concern, leader, transportation, jobCode)
            );

            // Create the ValueRange object
            ValueRange body = new ValueRange()
                    .setValues(data);

            // Append the data to the spreadsheet
            AppendValuesResponse result = sheetsService.spreadsheets().values()
                    .append(SPREADSHEET_ID, range, body)
                    .setValueInputOption("RAW")
                    .setInsertDataOption("INSERT_ROWS")
                    .execute();

            System.out.printf("Appended %d rows.\n", result.getUpdates().getUpdatedRows());
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public static String getNextJobCode(Sheets sheetsService, String jobOrderType) throws IOException {
        String range = "Sheet1!A:A";

        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();

        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            return jobOrderType + "-0001"; // If no data, start with 0001 for the specific job order type
        } else {
            // Filter and sort job codes to find the last job code of the given type
            String lastJobCode = values.stream()
                    .flatMap(List::stream)
                    .map(Object::toString)
                    .filter(jobCode -> jobCode.startsWith(jobOrderType))
                    .max(Comparator.naturalOrder())
                    .orElse(null);

            if (lastJobCode == null) {
                return jobOrderType + "-0001";
            } else {
                // Extract the numeric part from the last job code
                int lastDashIndex = lastJobCode.lastIndexOf('-');
                if (lastDashIndex != -1) {
                    String numericPart = lastJobCode.substring(lastDashIndex + 1);
                    try {
                        int lastJobCodeNumber = Integer.parseInt(numericPart);
                        int newJobCodeNumber = lastJobCodeNumber + 1;
                        return String.format("%s-%04d", jobOrderType, newJobCodeNumber);
                    } catch (NumberFormatException e) {
                        // Handle parsing error gracefully
                        e.printStackTrace();
                    }
                }
                return jobOrderType + "-0001"; // Default fallback if parsing fails
            }
        }
    }
}
