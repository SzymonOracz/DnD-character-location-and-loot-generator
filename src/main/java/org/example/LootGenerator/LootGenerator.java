package org.example.LootGenerator;

import java.util.Scanner;
public class LootGenerator {

    static Scanner scanner = new Scanner(System.in);

    public static void start () {
        System.out.println("Please enter character's level");
        int level = scanner.nextInt();
        if (level <= 0 || level > 20) {
            System.out.println("Sorry i cant generate loot for that level");
        } else {
            System.out.println("Reward for your adventure:");
            GenerateLootCategories.generateGold(level);
            GenerateLootArrays.createGemstonesList();
            GenerateLootCategories.generateGemstones(level);
            GenerateLootArrays.createScrolls();
            GenerateLootCategories.generateScrolls(level);
            GenerateLootArrays.createItems();
            GenerateLootCategories.generateItems(level);
            LootRequester.generateChatGPTRequest(LootRequester.lootMessage());
            for (String s : LootRequester.getArray()) {
                System.out.println(s);
            }
        }
    }
}
