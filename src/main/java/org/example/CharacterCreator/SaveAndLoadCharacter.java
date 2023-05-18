package org.example.CharacterCreator;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SaveAndLoadCharacter {

    static BufferedReader reader;

    public static void saveCharacter() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Ozymon\\IdeaProjects\\DNDCharacterTrackerAndLootGenerator\\src\\main\\java\\org\\example\\CharacterCreator\\SavedCharacters\\" + Character.name + ".txt"));
        writer.write(Character.name + "\n");
        writer.write( Character.alignment + " " + Character.race + " " + Character.gender + " " + Character.characterClass + "\n\n");
        for (String statistic : GenerateStatistics.statistics) {
            writer.write(statistic + "\n");
        }
        writer.write("\nEquipment:\n");
        for (String equipmentItem : Character.equipment) {
            writer.write(equipmentItem + "\n");
        }
        writer.write("\nProficiencies:\n");
        for (String prof : Character.profs) {
            writer.write(prof + "\n");
        }
        writer.write("\nSaving throws:\n");
        for (String savingThrow : Character.savingThrows) {
            writer.write(savingThrow + "\n");
        }
        writer.write("\nStory:\n");
        for (String line : Character.story) {
            writer.write(line + "\n");
        }
        writer.close();
    }

    public static void loadCharacter() {
        String directoryPath = "C:\\Users\\Ozymon\\IdeaProjects\\DNDCharacterTrackerAndLootGenerator\\src\\main\\java\\org\\example\\CharacterCreator\\SavedCharacters";
        File directory = new File(directoryPath);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
            if (files != null && files.length > 0) {
                System.out.println("Which character are you interested in:\n");
                for (File file : files) {
                    String fileName = file.getName();
                    String characterName = fileName.substring(0, fileName.lastIndexOf('.'));
                    System.out.println(characterName);
                }
                Scanner scanner = new Scanner(System.in);
                System.out.print("\nEnter the character's name to load it's file: ");
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