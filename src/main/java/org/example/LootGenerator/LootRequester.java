package org.example.LootGenerator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class LootRequester {
    private static ArrayList<String> GPTResponse;

    public static ArrayList<String> getArray() {
        return GPTResponse;
    }

    public static void generateChatGPTRequest(String lootMessage) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + lootMessage + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray choicesArray = jsonResponse.getJSONArray("choices");
            JSONObject firstChoice = choicesArray.getJSONObject(0);
            JSONObject messageObject = firstChoice.getJSONObject("message");
            String content = messageObject.getString("content");
            GPTResponse = new ArrayList<>();
            Pattern numberPattern = Pattern.compile("[0-9]+");
            if (numberPattern.matcher(content).find()) {
                String[] ideas = numberPattern.split(content);
                for (String line : ideas) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        System.out.println(line);
                        GPTResponse.add(line);
                    }
                }
            } else {
                String[] sentences = content.split("\\.");
                for (String sentence : sentences) {
                    sentence = sentence.trim();
                    if (!sentence.isEmpty()) {
                        GPTResponse.add(sentence);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String lootMessage () {
        return  "Please generate me 1 name of: " + GenerateLootCategories.getItems() +  " and describe what they do in 1 sentence. You must always start by giving item's name, do not write anything like Name: or description:. Just item's name them - then description.";
    }
}
