package org.example.LootGenerator;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class GenerateLootCategories {

    static ArrayList<String> itemsLoot;
    static Random random = new Random();

    public static String getItems() {
        return itemsLoot.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static void generateGold(int level) {
        double gold;
        int randomGold = random.nextInt(100) + 1;
            if (level < 7) {
                gold = (randomGold + 20) * (1.2 * level);
            } else if (level > 7 && level < 12) {
                gold = (randomGold + 20) * (1.5 * level);
            } else if (level > 12 && level < 16) {
                gold = (randomGold + 20) * (1.8 * level);
            } else {
                gold = (randomGold + 50) * (2.2 * level);
            }
            int yourGold = (int) Math.round(gold);
            System.out.println(yourGold + " gold pieces");
        }


    public static void generateGemstones (int playerLevel) {
        ArrayList<String> lootGemStones = new ArrayList<>();
        int minGems = 1;
        int maxGems;
        if (playerLevel < 5) {
            maxGems = 2;
        } else if (playerLevel < 10) {
            maxGems = 4;
        } else if (playerLevel < 15) {
            minGems = 2;
            maxGems = 6;
        } else {
            minGems = 4;
            maxGems = 8;
        }
        int numGems = random.nextInt(maxGems - minGems + 1) + minGems;
        if (playerLevel < 5) {
            for (int i = 0; i < numGems; i++) {
                int index = random.nextInt(GenerateLootArrays.createGemstonesList().size()-10);
                lootGemStones.add(GenerateLootArrays.createGemstonesList().get(index));
            }
        } else if (playerLevel <= 10) {
            int maxValueGem = 0;
            for (int i = 0; i < numGems; i++) {
                int index = random.nextInt(GenerateLootArrays.createGemstonesList().size()-6);
                String gem = GenerateLootArrays.createGemstonesList().get(index);
                if(gem.endsWith("(500gp)")) {
                    if (maxValueGem < 1) {
                        lootGemStones.add(gem);
                        maxValueGem++;
                    }
                } else {
                    lootGemStones.add(gem);
                }
            }
        } else if (playerLevel <= 15) {
            int maxValueGem = 0;
            for (int i = 0; i < numGems; i++) {
                int index = random.nextInt(GenerateLootArrays.createGemstonesList().size()-2);
                String gem = GenerateLootArrays.createGemstonesList().get(index);
                if(gem.endsWith("(1000gp)")) {
                    if (maxValueGem < 2) {
                        lootGemStones.add(gem);
                        maxValueGem++;
                    }
                } else {
                    lootGemStones.add(gem);
                }
            }
        } else {
            for (int i = 0; i < numGems; i++) {
                int index = random.nextInt(GenerateLootArrays.createGemstonesList().size());
                lootGemStones.add(GenerateLootArrays.createGemstonesList().get(index));
            }
        }
        for (String s: lootGemStones) {
            System.out.println(s);
        }
    }

    public static void generateScrolls (int playerLevel) {
        ArrayList<String> scrollsLoot = new ArrayList<>();
        int minScrolls = 1;
        int maxScrolls;
        if (playerLevel < 5) {
            maxScrolls = 1;
        } else if (playerLevel < 10) {
            maxScrolls = 2;
        } else if (playerLevel < 15) {
            maxScrolls = 3;
        } else {
            maxScrolls = 4;
        }
        int numScrolls = random.nextInt(maxScrolls - minScrolls + 1) + minScrolls;
        if (playerLevel < 5) {
            for (int i = 0; i < numScrolls; i++) {
                int index = random.nextInt(GenerateLootArrays.createScrolls().size() - 7);
                String scroll = GenerateLootArrays.createScrolls().get(index);
                scrollsLoot.add(scroll);
            }
        } else if (playerLevel <= 10) {
            int maxLevelScroll = 0;
            for (int i = 0; i < numScrolls; i++) {
                int index = random.nextInt(GenerateLootArrays.createScrolls().size()-5);
                String scroll = GenerateLootArrays.createScrolls().get(index);
                if(scroll.endsWith("(4th level)") || scroll.endsWith("(3th level)")) {
                    if (maxLevelScroll < 1) {
                        scrollsLoot.add(scroll);
                        maxLevelScroll++;
                    }
                } else {
                    scrollsLoot.add(scroll);
                }
            }
        }else if (playerLevel <= 15) {
            int maxLevelScroll = 0;
            for (int i = 0; i < numScrolls; i++) {
                int index = random.nextInt(GenerateLootArrays.createScrolls().size()-2);
                String scroll = GenerateLootArrays.createScrolls().get(index);
                if(scroll.endsWith("(7th level)") || scroll.endsWith("(6th level)")) {
                    if (maxLevelScroll < 2) {
                        scrollsLoot.add(scroll);
                        maxLevelScroll++;
                    }
                } else {
                    scrollsLoot.add(scroll);
                }
            }
        } else {
            for (int i = 0; i < numScrolls; i++) {
                int index = random.nextInt(GenerateLootArrays.createScrolls().size());
                scrollsLoot.add(GenerateLootArrays.createScrolls().get(index));
            }
        }
        for (String s: scrollsLoot) {
            System.out.println(s);
        }
    }
    public static void generateItems (int playerLevel) {
        itemsLoot = new ArrayList<>();
        int minItems = 1;
        int maxItems;
        if (playerLevel < 5) {
            maxItems = 1;
        } else if (playerLevel < 10) {
            maxItems = 2;
        } else if (playerLevel < 15) {
            maxItems = 3;
        } else {
            maxItems = 4;
        }
        int numItems = random.nextInt(maxItems - minItems + 1) + minItems;
            for (int i = 0; i < numItems; i++) {
                int index = random.nextInt(GenerateLootArrays.createItems().size());
                String item = GenerateLootArrays.createItems().get(index);
                itemsLoot.add(item);
            }
        }
    }
