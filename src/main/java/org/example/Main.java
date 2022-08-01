package org.example;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Creating URL request...");
        URL requestURL = ArticlesUtility.createURLTopHeadlines("?country=pl", "&category=business", "d6ef985e19c648239c89d506a2f00739");

        System.out.println("Sending API request...");
        JsonObject json = ArticlesUtility.getJsonFromAPIRequest(requestURL);

        System.out.println("Parsing request contents...");
        String articles = ArticlesUtility.getArticlesFromJson(json);

        System.out.println("Creating file with news...");
        String outputFile = "Poland_Business_News.txt";
        ArticlesUtility.writeArticlesToFile(articles, outputFile);

        // for pretty printing in console
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String articlesToPring = gson.toJson(json);
//        System.out.println(articlesToPring);

        System.out.println(json.get("totalResults") + " articles Were written to the file " + outputFile + "\nExiting program...");
    }
}