package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class ArticlesUtilityTest {

    @org.junit.Test
    public void createURLTopHeadlines() throws Exception {
        URL expectedURL = new URL("https://newsapi.org/v2/top-headlines?country=pl&category=business&apiKey=d6ef985e19c648239c89d506a2f00739");

        URL actualURL = ArticlesUtility.createURLTopHeadlines("?country=pl", "&category=business", "d6ef985e19c648239c89d506a2f00739");

        assertEquals(expectedURL, actualURL);
    }

    @org.junit.Test
    public void getJsonFromAPIRequest() throws Exception {
        String statusExpected = "ok";

        JsonObject json = ArticlesUtility.getJsonFromAPIRequest(new URL("https://newsapi.org/v2/top-headlines?country=pl&category=business&apiKey=d6ef985e19c648239c89d506a2f00739"));
        String statusActual = json.get("status").getAsString();

        assertEquals(statusExpected, statusActual);
    }

    @org.junit.Test
    public void getArticlesFromJson() {
        String expected = "Drożejące auta używane to oczywistość. Ale najbardziej drożeją dostawczaki - Autokult:Jak informuje AAA Auto aż o 23,4 proc. w ciągu ostatniego roku podrożały samochody typu van, czyli tzw. furgonetki. Nieczęsto się o tym dowiadujemy, ponieważ są one z reguły kupowane przez firmy.:blank\n";

        String contentsOfAPIRequest = "{\"status\":\"ok\",\"totalResults\":68,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Autokult.pl\"},\"author\":null,\"title\":\"Drożejące auta używane to oczywistość. Ale najbardziej drożeją dostawczaki - Autokult\",\"description\":\"Jak informuje AAA Auto aż o 23,4 proc. w ciągu ostatniego roku podrożały samochody typu van, czyli tzw. furgonetki. Nieczęsto się o tym dowiadujemy, ponieważ są one z reguły kupowane przez firmy.\",\"url\":\"https://biznes.autokult.pl/45566,drozejace-auta-uzywane-to-oczywistosc-ale-najbardziej-drozeja-vany\",\"urlToImage\":\"https://i.wpimg.pl/1920x0/m.autokult.pl/dsc0175-2ee9dbefb4fbcec3-be2cf41.jpg\",\"publishedAt\":\"2022-07-31T12:01:43Z\",\"content\":\"Wedug najnowszej analizy tej firmy, mediana cen samochodów uytkowych typu van wzrosa w cigu roku z 28,9 tys. z do 35,67 tys. z, czyli o ponad 23 proc. S to z reguy samochody o stosunkowo duym przebie… [+1613 chars]\"}]}";
        JsonObject jsonToTest = new Gson().fromJson(contentsOfAPIRequest, JsonObject.class);
        String actual = ArticlesUtility.getArticlesFromJson(jsonToTest);

        assertEquals(expected, actual);
    }
}