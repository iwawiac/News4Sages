package org.example;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URL;


public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("test");

        System.out.println("Creating URL request...");
        URL requestURL = ArticlesUtility.createURLTopHeadlines("?country=pl", "&category=business", "d6ef985e19c648239c89d506a2f00739");

        System.out.println("Sending API request...");
        JsonObject json = ArticlesUtility.getJsonFromAPIRequest(requestURL);

        System.out.println("Parsing request contents...");
        String articles = ArticlesUtility.getArticlesFromJson(json);

        System.out.println("Creating file with news...");
        ArticlesUtility.writeArticlesToFile(articles, "News_Poland_Business.txt");
    }
}