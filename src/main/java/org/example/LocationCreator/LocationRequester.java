package org.example.LocationCreator;
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

public class LocationRequester {

    private static ArrayList<String> GPTResponse;

    public static ArrayList<String> getArray() {
        return GPTResponse;
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

    public static String typeMessage() {
       return  "give me 10 DnD location ideas where players will go for and adventure without their names or description";
    }
    public static String nameMessage(String locationType) {
        return "give me 10 name ideas for DnD location called" + locationType;
    }
    public static String atmosphereMessage(String locationType, String locationName) {
        return "give me 10 ideas for an atmpshere in DnD for " + locationType + " named " + locationName  + " You must add a number before each location";
    }
    public static String NPCMessage (String locationType, String locationName) {
        return "give me 10 ideas for a random NPC in DnD for "  + locationType +  " named " + locationName + " You must add a number before each NPC";
    }
    public static String randomEncountersMessage(String locationType, String locationName, String NPC) {
        return "give me 10 ideas for and random encounters in DnD for "  + locationType +  " named " + locationName + " Important NPC int this location is " + NPC +
                " You must add a number before each encounter";
    }
    public static String monstersMessage(String locationType, String locationName, String NPC) {
        return "give me 10 ideas for and monsters in DnD for"  + locationType +  " named " + locationName + " Important NPC int this location is " + NPC  + " You must add a number before each monster";
    }
    public static String storyMessage(String locationType, String locationName, String NPC) {
        return "give me background story in DnD for "  + locationType +  " named " + locationName + " Important NPC int this location is " + NPC + " Please make the history expanded" +
                "Add info about that location's history, inhabitants, past conflicts (if those happened). Make this story entertaining and add 1 plot twist to the plot. make the story exactly 28 sentences long.";
    }
}
