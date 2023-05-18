package org.example.LocationCreator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SaveAndLoadLocation {
    static BufferedReader reader;

    public static void saveLocation() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Ozymon\\IdeaProjects\\DNDCharacterTrackerAndLootGenerator\\src\\main\\java\\org\\example\\LocationCreator\\SavedLocations\\" + Location.name + ".txt"));
        writer.write(Location.name + "\n");
        writer.write( Location.type + ": " + Location.name + "\nImportant NPC of this location: " + Location.importantNPC  + "\n\nAtmosphere characteristic:\n");
        for (String atmosphereCharacteristic : Location.atmosphereCharacteristics) {
            writer.write(atmosphereCharacteristic + "\n");
        }
        writer.write("\nRandom Encounters:\n");
        for (String randomEncounter : Location.randomEncounters) {
            writer.write(randomEncounter + "\n");
        }
        writer.write("\nMonsters:\n");
        for (String monster : Location.monsters) {
            writer.write(monster + "\n");
        }
        writer.write("\nStory:\n");
        for (String line : Location.story) {
            writer.write(line + "\n");
        }
        writer.close();
    }

    public static void loadLocation() {
        String directoryPath = "C:\\Users\\Ozymon\\IdeaProjects\\DNDCharacterTrackerAndLootGenerator\\src\\main\\java\\org\\example\\LocationCreator\\SavedLocations";
        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
            if (files != null && files.length > 0) {
                System.out.println("Which location are you interested in:\n");
                for (File file : files) {
                    String fileName = file.getName();
                    String characterName = fileName.substring(0, fileName.lastIndexOf('.'));
                    System.out.println(characterName);
                }
                Scanner scanner = new Scanner(System.in);
                System.out.print("\nEnter the location's name to load it's file: ");
                String userInput = scanner.nextLine();
                String filePath = directoryPath + "\\" + userInput + ".txt";
                File selectedFile = new File(filePath);
                if (selectedFile.exists()) {
                    try {
                        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                        System.out.println("File loaded successfully:\n" + fileContent);
                    } catch (IOException e) {
                        System.out.println("Error occurred while reading the file.");
                    }
                }
            } else {
                System.out.println("No characters found in the directory.");
            }
        } else {
            System.out.println("Invalid directory path.");
        }
    }
}
