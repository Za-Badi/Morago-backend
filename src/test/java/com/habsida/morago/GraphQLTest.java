package com.habsida.morago;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphQLTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static String getCoinByIdQuery;
    private static String getAllCoinsQuery;
    private static int queryId;

    @BeforeAll
    public static void setup() throws IOException {
        String queries = Files.lines(Path.of(new ClassPathResource("graphql/query_tests.graphql").getURI()))
                .collect(Collectors.joining("\n"));
        getCoinByIdQuery = queries.split("query getCoinById")[1].split("query getAllCoins")[0].trim();
        getAllCoinsQuery = queries.split("query getAllCoins")[1].trim();

        // Extract the id from the getCoinById query
        Pattern pattern = Pattern.compile("getCoinById\\(id: (\\d+)\\)");
        Matcher matcher = pattern.matcher(getCoinByIdQuery);
        if (matcher.find()) {
            queryId = Integer.parseInt(matcher.group(1));
        } else {
            throw new RuntimeException("No id found in getCoinById query");
        }

        // Escape newlines in queries
        getCoinByIdQuery = getCoinByIdQuery.replace("\n", "\\n").replace("\"", "\\\"");
        getAllCoinsQuery = getAllCoinsQuery.replace("\n", "\\n").replace("\"", "\\\"");
    }

    private static final String GRAPHQL_ENDPOINT = "/graphql";

    @Test
    public void testGetCoinById() {
        String query = getCoinByIdQuery;
        HttpEntity<String> request = new HttpEntity<>("{\"query\":\"query getCoinById " + query + "\"}");

        ResponseEntity<String> response = restTemplate.exchange(GRAPHQL_ENDPOINT, HttpMethod.POST, request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        try {
            JSONObject jsonResponse = new JSONObject(response.getBody());

            // Check if the data field is null
            if (jsonResponse.has("data") && !jsonResponse.isNull("data")) {
                JSONObject data = jsonResponse.getJSONObject("data");
                if (data.has("getCoinById") && !data.isNull("getCoinById")) {
                    JSONObject getCoinById = data.getJSONObject("getCoinById");
                    assertEquals(queryId, getCoinById.getInt("id"));
                    // Additional assertions can be added for other fields if needed
                } else {
                    System.out.println("No Coin with id: " + queryId);
                }
            } else {
                System.out.println("Query did not return data");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Failed to parse JSON response");
        }
    }

    @Test
    public void testGetAllCoins() {
        HttpEntity<String> request = new HttpEntity<>("{\"query\":\"query getAllCoins " + getAllCoinsQuery + "\"}");

        ResponseEntity<String> response = restTemplate.exchange(GRAPHQL_ENDPOINT, HttpMethod.POST, request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        try {
            JSONObject jsonResponse = new JSONObject(response.getBody());

            if (jsonResponse.has("data") && !jsonResponse.isNull("data")) {
                JSONObject data = jsonResponse.getJSONObject("data");
                if (data.has("getAllCoins") && !data.isNull("getAllCoins")) {
                    JSONArray getAllCoins = data.getJSONArray("getAllCoins");
                    for (int i = 0; i < getAllCoins.length(); i++) {
                        JSONObject coin = getAllCoins.getJSONObject(i);
                        int id = coin.getInt("id");
                        double coinValue = coin.getDouble("coin");
                        double won = coin.getDouble("won");
                        // Additional assertions can be added for other fields if needed
                        System.out.println("Coin ID: " + id + ", Coin: " + coinValue + ", Won: " + won);
                    }
                } else {
                    System.out.println("No Coins found");
                }
            } else {
                System.out.println("Query did not return data");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Failed to parse JSON response");
        }
    }
}
