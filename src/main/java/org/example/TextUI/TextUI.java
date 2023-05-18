package org.example.TextUI;
import org.example.CharacterCreator.CharacterCreator;
import org.example.CharacterCreator.SaveAndLoadCharacter;
import org.example.LocationCreator.SaveAndLoadLocation;
import org.example.LocationCreator.LocationCreator;
import org.example.LootGenerator.LootGenerator;
import java.io.IOException;
import java.util.Scanner;

public class TextUI {
    Scanner scanner;

        public TextUI() {
            this.scanner = new Scanner(System.in);
        }

        public void start() throws IOException, InterruptedException {
            while (true) {
                System.out.println(printWelcomeMessage());
                String command = scanner.nextLine();
                String categoryCommand;
                if (command.equals("exit")) {
                    System.out.println("bye bye!");
                    break;
                }
                switch (command) {
                    case "character":
                        do {
                            CharacterCreator character = new CharacterCreator();
                            character.start();
                            System.out.println("Enter 'again' to create another character or 'back' to go back:");
                            categoryCommand = scanner.nextLine();
                        } while (!categoryCommand.equals("back"));
                        break;

                    case "location":
                        do {
                            LocationCreator.start();
                            System.out.println("Enter 'again' to create another location or 'back' to go back:");
                            categoryCommand = scanner.nextLine();
                        } while (!categoryCommand.equals("back"));
                        break;

                    case "showCharacters":
                        do {
                            SaveAndLoadCharacter.loadCharacter();
                            System.out.println("\nEnter 'again' to see another character or 'back' to go back:");
                            categoryCommand = scanner.nextLine();
                        } while (!categoryCommand.equals("back"));
                        break;

                    case "showLocations":
                        do {
                            SaveAndLoadLocation.loadLocation();
                            System.out.println("\nEnter 'again' to see another location or 'back' to go back:");
                            categoryCommand = scanner.nextLine();
                        } while (!categoryCommand.equals("back"));
                        break;

                    case "loot":
                        do {
                            LootGenerator.start();
                            System.out.println("\nEnter 'again' to generate another loot or 'back' to go back:");
                            categoryCommand = scanner.nextLine();
                        } while (!categoryCommand.equals("back"));
                        break;

                    default:
                        System.out.println("Sorry, there's no such command.");
                        break;
                }
            }
        }

        public String printWelcomeMessage() {
            return "Welcome to my DnD5e app!\n\nCommand list: \n" +
                    "character - create your own DnD character.\n" +
                    "location - create a location.\n" +
                    "showCharacters - see a list of your characters.\n" +
                    "showLocations - see a list of your locations.\n" +
                    "loot - generate random loot from your adventure.\n" +
                    "exit - close the app.";
        }
    }


