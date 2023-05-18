package org.example.LocationCreator;

import java.io.IOException;
import java.util.Scanner;

public class LocationCreator {
    static Scanner scanner = new Scanner(System.in);
    static String musicPath = "C:\\Users\\Ozymon\\IdeaProjects\\DNDCharacterLocationAndLootGenerator\\Music\\LightsWillGuideYouHomeDnDJavaMusic.wav";

    public static void start() throws IOException {
        System.out.println("Welcome to location creator\n" +
                "We will make your location with a background story in less that 2 minutes!\n" +
                "Sometimes generating options for your location may take up to 30 seconds so please be patient.\n" +
                "First of all what type of location would you like to make?\n" +
                "These are just some random ideas. Feel free to write your own!");
        LocationRequester.generateChatGPTRequest(LocationRequester.typeMessage());
        String locationType = scanner.nextLine();

        System.out.println("Now that we know that your location is a " + locationType + " what name would you like to give it?\n" +
                "These are just some random ideas. Feel free to write your own!");
        LocationRequester.generateChatGPTRequest(LocationRequester.nameMessage(locationType));
        String locationName = scanner.nextLine();

        System.out.println("Now that we got that what atmosphere characteristics would you like your location to have?\n" +
        "Add 4 ideas of your choosing or write your own!");
        LocationRequester.generateChatGPTRequest(LocationRequester.atmosphereMessage(locationType, locationName));
        for(int i = 0 ; i < 4; i++) {
            String atmosphere = scanner.nextLine();
            Location.addAtmosphere(atmosphere);
        }
        System.out.println("We know so much about your location yet we are half way there.\n" +
                "Now we will ad an important NPC to your location");
        LocationRequester.generateChatGPTRequest(LocationRequester.NPCMessage(locationType, locationName));
        String NPC = scanner.nextLine();
        new Location(locationType, locationName, NPC);

        System.out.println("Now that we know that we will add some random encounters to your location.\n" +
                "Add 4 ideas of your choosing type write own!");
        LocationRequester.generateChatGPTRequest(LocationRequester.randomEncountersMessage(locationType, locationName, NPC));
        for(int i = 0 ; i < 4; i++) {
            String randomEncounter = scanner.nextLine();
            Location.addRandomEncounters(randomEncounter);
        }

        System.out.println("We are almost there. lets add some monsters!\n" +
                "Add 4 ideas of your choosing type write own!");
        LocationRequester.generateChatGPTRequest(LocationRequester.monstersMessage(locationType, locationName, NPC));
        for(int i = 0 ; i < 4; i++) {
            String randomEncounter = scanner.nextLine();
            Location.addMonsters(randomEncounter);
        }
        System.out.println("In a second you will see your location's info and hear it's story!\n" +
                "(Story should be printed as the narrator read it but due to unpredictability of used API it may be printed all at once.)");
        LocationRequester.generateChatGPTRequest(LocationRequester.storyMessage(locationType, locationName, NPC));
        for(String line: LocationRequester.getArray()) {
            Location.addStory(line);
        }
        Location.printLocation(musicPath);
        System.out.println("\n\nIf you like this location you can save it by simply typing save, or if you want to generate again type create\n" +
                "For generating a location again type again, if you want to go back to main menu type back.");
        String save = scanner.nextLine();
        if(save.equals("save")) {
            System.out.println("Location saved!\n" +
                    "For generating a location again type again, if you want to go back to main menu type back.");
            SaveAndLoadLocation.saveLocation();
        }
    }
}
