package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ArticlesUtility {


    public static URL createURLTopHeadlines(String country, String category, String APIKey) throws MalformedURLException {
        String endpoint = "https://newsapi.org/v2/top-headlines";
        return new URL(endpoint + country + category + "&apiKey=" + APIKey);
    }

    public static JsonObject getJsonFromAPIRequest(URL request) throws IOException {
        Scanner scanner = new Scanner(request.openStream(), StandardCharsets.UTF_8.toString());
        scanner.useDelimiter("\\A");
        String jsonString = scanner.hasNext() ? scanner.next() : "";
        JsonObject requestJsonObject = new Gson().fromJson(jsonString, JsonObject.class);
        scanner.close();
        return requestJsonObject;
    }

    public static String getArticlesFromJson(JsonObject json) {
        JsonArray articlesArray = json.get("articles").getAsJsonArray();
        StringBuilder articles = new StringBuilder();

        for (int i = 0; i < articlesArray.size(); i++) {

            String articleTitle;
            String articleAuthor;
            String articleDescription;

            try {
                articleTitle = articlesArray.get(i).getAsJsonObject().get("title").getAsString();
            } catch (UnsupportedOperationException e) {
                articleTitle = "blank"; // if title == null we get exception, and it is replaced by "blank"
            }
            try {
                articleAuthor = articlesArray.get(i).getAsJsonObject().get("author").getAsString();
            } catch (UnsupportedOperationException e) {
                articleAuthor = "blank";
            }
            try {
                articleDescription = articlesArray.get(i).getAsJsonObject().get("description").getAsString();
            } catch (UnsupportedOperationException e) {
                articleDescription = "blank";
            }

            String titleDescriptionAuthorToWrite = String.join(":", articleTitle, articleDescription, articleAuthor);
            articles.append(titleDescriptionAuthorToWrite).append("\n");
        }
        return articles.toString();
    }

    public static void writeArticlesToFile(String articlesToWrite, String FileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FileName));
        writer.write(articlesToWrite);
        writer.close();
    }
}
