package com.company;
import com.company.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        URL url = new URL("https://cat-fact.herokuapp.com/facts");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        int code = httpURLConnection.getResponseCode();
        System.out.println("Code: " + code);
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream()))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String jsonObj = String.valueOf(stringBuilder);
            ObjectMapper objectMapper = new ObjectMapper();
            List<User> userList;
            userList = objectMapper.readValue(jsonObj, new TypeReference<List<User>>() {});
            for (User user:userList) {
                System.out.println(user+"\n");
            }
        }
    }
}
