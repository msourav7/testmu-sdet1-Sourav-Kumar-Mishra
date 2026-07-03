//package ai;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import config.ConfigReader;
//import okhttp3.*;
//
//import java.io.IOException;
//
//public class GeminiClient {
//
//    private static final OkHttpClient client =
//            new OkHttpClient.Builder()
//                    .connectTimeout(java.time.Duration.ofSeconds(60))
//                    .readTimeout(java.time.Duration.ofSeconds(60))
//                    .writeTimeout(java.time.Duration.ofSeconds(60))
//                    .build();
//
//    private static final String API_KEY =
//            ConfigReader.get("gemini.api.key");
//
//    private static final String MODEL =
//            ConfigReader.get("gemini.model");
//
//    private static final String URL =
//            "https://generativelanguage.googleapis.com/v1beta/models/"
//                    + MODEL +
//                    ":generateContent?key=" +
//                    API_KEY;
//
//    public static String generate(String prompt) {
//
//        JsonObject text = new JsonObject();
//        text.addProperty("text", prompt);
//
//        JsonObject part = new JsonObject();
//        part.add("parts", new JsonArray());
//        part.getAsJsonArray("parts").add(text);
//
//        JsonArray contents = new JsonArray();
//        contents.add(part);
//
//        JsonObject body = new JsonObject();
//        body.add("contents", contents);
//
//        RequestBody requestBody = RequestBody.create(
//                body.toString(),
//                MediaType.parse("application/json")
//        );
//
//        Request request = new Request.Builder()
//                .url(URL)
//                .post(requestBody)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//
//            if (!response.isSuccessful()) {
//
//                throw new RuntimeException(
//                        "Gemini API Error : " +
//                                response.body().string());
//
//            }
//
//            String json = response.body().string();
//
//            JsonObject object =
//                    com.google.gson.JsonParser
//                            .parseString(json)
//                            .getAsJsonObject();
//
//            return object
//                    .getAsJsonArray("candidates")
//                    .get(0)
//                    .getAsJsonObject()
//                    .getAsJsonObject("content")
//                    .getAsJsonArray("parts")
//                    .get(0)
//                    .getAsJsonObject()
//                    .get("text")
//                    .getAsString();
//
//        }
//
//        catch (IOException e) {
//
//            throw new RuntimeException(e);
//
//        }
//
//    }
//
//}

package ai;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import config.ConfigReader;
import okhttp3.*;

import java.io.IOException;

public class GeminiClient {

    private static final OkHttpClient client =
            new OkHttpClient.Builder()
                    .connectTimeout(java.time.Duration.ofSeconds(60))
                    .readTimeout(java.time.Duration.ofSeconds(60))
                    .writeTimeout(java.time.Duration.ofSeconds(60))
                    .build();

    public static String generate(String prompt) {

        String apiKey = ConfigReader.get("gemini.api.key");
        String model = ConfigReader.get("gemini.model");

        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException("Gemini API key not found.");
        }

        if (model == null || model.isBlank()) {
            throw new RuntimeException("Gemini model not found.");
        }

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/"
                        + model
                        + ":generateContent?key="
                        + apiKey;

        JsonObject text = new JsonObject();
        text.addProperty("text", prompt);

        JsonObject part = new JsonObject();
        part.add("parts", new JsonArray());
        part.getAsJsonArray("parts").add(text);

        JsonArray contents = new JsonArray();
        contents.add(part);

        JsonObject body = new JsonObject();
        body.add("contents", contents);

        RequestBody requestBody = RequestBody.create(
                body.toString(),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new RuntimeException(
                        "Gemini API Error : " +
                                response.body().string()
                );
            }

            String json = response.body().string();

            JsonObject object =
                    com.google.gson.JsonParser
                            .parseString(json)
                            .getAsJsonObject();

            return object
                    .getAsJsonArray("candidates")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts")
                    .get(0)
                    .getAsJsonObject()
                    .get("text")
                    .getAsString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}