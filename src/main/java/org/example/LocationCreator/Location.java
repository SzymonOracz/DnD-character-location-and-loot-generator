package org.example.LocationCreator;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Location {

    protected static String type;
    protected static String name;
    protected static String importantNPC;
    protected static final ArrayList<String> atmosphereCharacteristics = new ArrayList<>();
    protected static final ArrayList<String> randomEncounters= new ArrayList<>();
    protected static final ArrayList<String> monsters = new ArrayList<>();
    protected static final ArrayList<String> story = new ArrayList<>();

    public  Location(String type, String name, String importantNPC) {
        Location.type = type;
        Location.name = name;
        Location.importantNPC = importantNPC;
    }

    public static void printLocation(String filepath) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            System.out.println(name + ": " + type + "\nIn this location players will encounter " + importantNPC);
            printAtmosphereCharacteristics();
            printRandomEncounters();
            printMonsters();
            executorService.execute(() -> playMusic(filepath));
            System.out.println("\nStory:");
            voice.deallocate();
            printStory();
        } else {
            System.out.println("Error in getting voice");
        }
    }
    public static void printStory() {
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
    public static void addAtmosphere(String atmosphere) {
        atmosphereCharacteristics.add(atmosphere);
    }

    public static void addRandomEncounters(String randomEncounter) {
        randomEncounters.add(randomEncounter);
    }

    public static void addMonsters(String monster) {
        monsters.add(monster);
    }

    public static void addStory (String storyLine) {
        story.add(storyLine);
    }

    public static void printAtmosphereCharacteristics() {
        System.out.println("\n\n Characteristics about that place:\n");
        for(String s: atmosphereCharacteristics) {
            System.out.println(s);
        }
    }
    public static void printRandomEncounters() {
        System.out.println("\n\n Random encounters awaiting players:\n");
        for(String s: randomEncounters) {
            System.out.println(s);
        }
    }
    public static void printMonsters() {
        System.out.println("\n\n Monsters inhabiting that place:\n");
        for(String s: monsters) {
            System.out.println(s);
        }
    }
    public static void playMusic(String musicLocation) {
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
