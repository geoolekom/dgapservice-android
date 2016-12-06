package com.geoolekom.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by geoolekom on 06.12.16.
 */

public class JsonFactory {
    public static JSONObject getFromUrl(String url) throws IOException, JSONException {
        InputStream input = new URL(url).openStream();
        StringBuilder builder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
        } finally {
            input.close();
        }
        return new JSONObject(builder.toString());
    }
}
