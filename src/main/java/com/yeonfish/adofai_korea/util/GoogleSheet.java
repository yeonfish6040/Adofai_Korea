package com.yeonfish.adofai_korea.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.yeonfish.adofai_korea.DevController.logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleSheet {
    private final logger log = new logger();

    private final String Application_Name = "Google Sheets API Java Quickstart";
    private final JsonFactory JSON_Factory = GsonFactory.getDefaultInstance();
    private final String Token_Path = "tokens";
    private String SpreadsheetId;
    private Credential Credential;


    private static final List<String> Scopes =
            Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/secret/google_sheet_credentials.json";

    public GoogleSheet(String id) {
        this.SpreadsheetId = id;
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = getClass().getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_Factory, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_Factory, clientSecrets, Scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(Token_Path)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Prints the names and majors of students in a sample spreadsheet:
     * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
     */
    public Spreadsheet getSheetData(String range) throws IOException, GeneralSecurityException {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        if (Credential == null) Credential = getCredentials(HTTP_TRANSPORT);
        Sheets service =
                new Sheets.Builder(HTTP_TRANSPORT, JSON_Factory, Credential)
                        .setApplicationName(Application_Name)
                        .build();
        Sheets.Spreadsheets.Get request = service.spreadsheets().get(SpreadsheetId);
        // Hyperlink and text
        request.setFields("sheets(data(rowData(values(hyperlink,formattedValue))))");
        request.setRanges(Collections.singletonList(range));
        request.execute();
        Spreadsheet response = request.execute();
        return response;
    }
}
