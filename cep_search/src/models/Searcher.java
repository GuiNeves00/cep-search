package models;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Searcher {
    private HttpClient client;

    public Searcher() {
        this.client = HttpClient.newHttpClient();
    }

    public String receiveFrom(String search) throws IOException, InterruptedException {
        // return api's response from given search

        search = this.ensureCepNumber(search); // ensures the used cep is digits only
        String encodedSearch = URLEncoder.encode(search, StandardCharsets.UTF_8);
        String address = "https://viacep.com.br/ws/" + encodedSearch + "/json/";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private String ensureCepNumber(String num) {
        // transforms str into digits only (example: 11111-111 -> "-" would be removed)
        return String.valueOf(
                Integer.valueOf(
                        num.replaceAll("[^0-9]", "")));
    }

    public boolean isNumeric(String str) {
        // checks if given string is made of numbers only
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
