package org.example.CharacterCreator;
import java.io.IOException;
import java.util.Scanner;

public class CharacterCreator {

    Scanner scanner = new Scanner(System.in);
    String musicPath = "C:\\Users\\Ozymon\\IdeaProjects\\DnDCharacterLocationAndLootGenerator\\Music\\LightsWillGuideYouHomeDnDJavaMusic.wav";
    String wrongInput = "That doesn't seem like a good idea. Try something else.";
    String race = "";
    String characterClass = "";
    String alignment = "";
    String pattern = "^[\\w\\s-]{3,}$";

    public void start() throws IOException, InterruptedException {
        System.out.println("Welcome to character creator\n" +
                "Here we will make your character with a background.\n" +
                "First of all which race would you like your Adventurer to be?\n" +
                "Due to used API limitations only this races can be printed, but feel free to write your own.\n");
        CharacterRequester.yourCharacterRace();
        while (race.length() < 3 || !race.matches(pattern)) {
            System.out.print("I want to be: ");
            race = scanner.nextLine();
            if (race.length() < 3 || !race.matches(pattern)) {
                System.out.println(wrongInput);
            }
        }
        System.out.println("Now that we know you are " + race + " what will be your class?\n" +
                "Due to used API limitations only this classes can be chosen. \n" +
                "Any other class will break the creator\n");
        CharacterRequester.yourCharacterClassOptions();
        while (characterClass.length() <= 3 || !characterClass.matches("\\w+")) {
            System.out.print("I want to play as: ");
            characterClass = scanner.nextLine();
            if (characterClass.length() <= 3 || !characterClass.matches("\\w+")) {
                System.out.println(wrongInput);
            }
        }
        CharacterRequester.yourCharacterClass(characterClass);
        System.out.println("Wise choice. It is time to choose alignment of your " + race + " " + characterClass);
        CharacterRequester.yourCharacterAlignment();
        while (alignment.length() <= 3 || !alignment.matches(pattern)) {
            System.out.print("I will be: ");
            alignment = scanner.nextLine();
            if (alignment.length() <= 3 || !alignment.matches(pattern)) {
                System.out.println(wrongInput);
            }
        }
        System.out.println("Very interesting. Now please write a gender of your " + race + " " + alignment + " " +
                characterClass + "\nYou can write any gender you feel will fit.");
        String gender = scanner.nextLine();
        System.out.println("We are almost ready. It is about time for the most important decision to be taken.\n" +
                "Please write your character's name.\n" +
                "If there is no cool name idea in your head don't worry. Just type: generate!");
        String name = scanner.nextLine();
        while (name.equals("generate")) {
            CharacterRequester.generateChatGPTRequest(CharacterRequester.nameMessage(race, alignment, characterClass, gender));
            System.out.println("\nIs there a name for you here? simply copy and paste it.\n" +
                    "If you don't like any name from the list type: again.\n");
            String command = scanner.nextLine();
            if (command.equals("again")) {
                CharacterRequester.generateChatGPTRequest(CharacterRequester.nameMessage(race, alignment, characterClass, gender));
            } else {
                name = command;
            }
        }
        Character character = new Character(race, characterClass, alignment, gender, name);
        System.out.println("\nNow let's make some background story shall we?\n" +
                "You will listen to it in a second.\n" +
                "Please remember that your character's story is AI generated and may contain minor mistakes or repetitions\n");
        CharacterRequester.generateChatGPTRequest(CharacterRequester.storyMessage(race, characterClass, alignment, gender, name));
        character.printCharacter(musicPath);
        System.out.println("\n\nIf you like this Adventurer you can save it by simply typing save, or if you want to generate again type create\n" +
                "For generating a character again type again, if you want to go back to main menu type back.");
        String save = scanner.nextLine();
        if(save.equals("save")) {
            System.out.println("Character saved!\n" +
                    "For generating a character again type again, if you want to go back to main menu type back.");
            SaveAndLoadCharacter.saveCharacter();
        }
    }
}