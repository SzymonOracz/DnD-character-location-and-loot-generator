package org.example.CharacterCreator;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;


public class CharacterRequester {

    String apiKey= "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";
    static HttpClient client;
    static HttpResponse response;
    static ArrayList<String> story;
    public static ArrayList<String> getStory() {
        return story;
    }

    public static void yourCharacterRace() throws IOException, InterruptedException {
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dnd5eapi.co/api/races"))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        CharacterMapper.parseRace(response);
    }

    public static void yourCharacterClassOptions() throws IOException, InterruptedException {
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dnd5eapi.co/api/classes"))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        CharacterMapper.parseClassOptions(response);
    }

    public static void yourCharacterClass(String characterClass) throws IOException, InterruptedException {
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dnd5eapi.co/api/classes/" + characterClass.toLowerCase()))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        CharacterMapper.parseClass(response);
    }

    public static void yourCharacterAlignment() throws IOException, InterruptedException {
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dnd5eapi.co/api/alignments"))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        CharacterMapper.parseAlignments(response);
    }

    public static void generateChatGPTRequest(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray choicesArray = jsonResponse.getJSONArray("choices");
            JSONObject firstChoice = choicesArray.getJSONObject(0);
            JSONObject messageObject = firstChoice.getJSONObject("message");
            String content = messageObject.getString("content");
            String[] sentences = content.split("\\.");
            story = new ArrayList<>();
            for (String sentence : sentences) {
                if (sentence.length() < 30) {
                    System.out.println(sentence.trim());
                } else if (sentence.length() > 30) {
                    sentence = sentence.trim();
                    story.add(sentence);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String nameMessage(String race, String alignment, String characterClass, String gender) {
        return "please generate me a list of 10 suitable fantasy names with surnames for"
                + race + " " + alignment + " " + characterClass + " " + gender + "Separate each generation with a space and please generate without numbers";
    }

    public static String storyMessage(String race, String characterClass, String alignment, String gender, String name) {
        return "please generate a background story for my" + race + " "+ characterClass + " " +
                "(if a class is a healer they should avoid brutality) " + alignment + " " + gender +
                "The name is " + name + ". I want this story to contain a place of birth and extended info about it. " +
                "I also want the story to contain 2 personality traits and 1 flaw with descriptions how he got them in the past." +
                " 1 personal struggle they will have to overcome during their journeys and a motivation in their life. " +
                "Please add 1 magic item, a gift from a family member or a mentor is preferable but not necessary describe how character got it and what the item does " +
                "(it should but may not be sentimental for the character)." +
                "Please refer to this item as a Unique item or treasure." +
                "Do not write anything before story starts. At the end please add something short to hype the player about the Character " +
                "and adventures they will have at the end. The story should be expanded. Make the story exactly 28 sentences long. " +
                "Please stay within Dungeons And Dragons universe and do not repeat yourself";
    }
}
