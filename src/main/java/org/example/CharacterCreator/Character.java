package org.example.CharacterCreator;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Character {

    protected static String race;
    protected static String name;
    protected static String gender;
    protected static String alignment;
    protected static String characterClass;
    protected static ArrayList<String> story;
    protected static final ArrayList<String> profs = new ArrayList<>();
    protected static final ArrayList<String> savingThrows = new ArrayList<>();
    protected static final ArrayList<String> equipment = new ArrayList<>();

    public Character(String race, String characterClass, String alignment, String gender, String name) {
        Character.race = race;
        Character.characterClass = characterClass;
        Character.alignment = alignment;
        Character.gender = gender;
        Character.name = name;
    }

    public void printCharacter(String filepath) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            System.out.println(name + ": \n" + alignment + " " + race + " " + gender + " " + characterClass + "\n");
            GenerateStatistics.generateStats();
            printEQ();
            printProf();
            printST();
            System.out.println("\nSpeed: 30 feet / turn");
            executorService.execute(() -> playMusic(filepath));
            System.out.println("\nStory:");
            voice.deallocate();
            addStory();
            printStory();
        } else {
            System.out.println("Error in getting voice");
        }
    }

    public void printStory() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            for (String sentence : story) {
                System.out.println(sentence);
                voice.speak(sentence);
            }
            voice.deallocate();
        } else {
            System.out.println("Error in getting voice");
        }
    }

    public static void addEQ(String eq) {
        equipment.add(eq);
    }
    public static void addProf (String prof) {
        profs.add(prof);
    }
    public static void addST (String ST) {
        savingThrows.add(ST);
    }
    public void addStory() {
        story = CharacterRequester.getStory();
    }

    public static boolean checkProfs(String proficiency) {
        for (String s : profs) {
            if (s.equals(proficiency)) {
                System.out.println("This proficiency is already on the list");
                return false;
            }
        }
        return true;
    }

    public static void printEQ() {
        System.out.println("\nYour equipment:");
        for(String s: equipment) {
            System.out.println(s);
        }
    }

    public static void printProf() {
        System.out.println("\nYour profs:");
        for(String s: profs) {
            System.out.println(s);
        }
    }

    public static void printST() {
        System.out.println("\nYour saving Throws:");
        for(String s: savingThrows) {
            System.out.println(s);
        }
    }
    public void playMusic(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("No file found");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


