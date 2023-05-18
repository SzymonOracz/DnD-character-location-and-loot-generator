package org.example.LootGenerator;

import java.util.ArrayList;

public class GenerateLootArrays {
    static ArrayList<String> gemstones;
    static ArrayList<String> scrolls;
    static ArrayList<String> items;

    public static ArrayList<String> createGemstonesList() {
        gemstones = new ArrayList<>();
        gemstones.add("Agate  (10gp)");
        gemstones.add("Azurite  (10gp)");
        gemstones.add("Quarts (blue)  (10gp)");
        gemstones.add("Hematite  (10gp)");
        gemstones.add("Lapis lazui  (10gp)");
        gemstones.add("Malachite  (10gp)");
        gemstones.add("Bloodstone  (50gp)");
        gemstones.add("Moonstone  (50gp)");
        gemstones.add("Onyx  (50gp)");
        gemstones.add("Amber  (50gp)");
        gemstones.add("Amethyst  (50gp)");
        gemstones.add("Jade  (50gp)");
        gemstones.add("Pearl (white)  (50gp)");
        gemstones.add("Garnet  (500gp)");
        gemstones.add("Black pearl  (500gp)");
        gemstones.add("Topaz (gold)  (500gp)");
        gemstones.add("Topaz (yellow)  (500gp)");
        gemstones.add("Emerald  (1000gp)");
        gemstones.add("Opel  (1000gp)");
        gemstones.add("Sapphire  (1000gp)");
        gemstones.add("Ruby  (1000gp) ");
        gemstones.add("Emerald (clear)  (5000gp)");
        gemstones.add("Diamond  (5000gp)");
        return gemstones;
    }
    public static ArrayList<String> createScrolls() {
        scrolls = new ArrayList<>();
        scrolls.add("Scroll (1st level)");
        scrolls.add("Scroll (2nd level)");
        scrolls.add("Scroll (3rd level)");
        scrolls.add("Scroll (4th level)");
        scrolls.add("Scroll (5th level)");
        scrolls.add("Scroll (6th level)");
        scrolls.add("Scroll (7th level)");
        scrolls.add("Scroll (8th level)");
        scrolls.add("Scroll (9th level)");
        return scrolls;
    }

    public static ArrayList<String> createItems() {
        items = new ArrayList<>();
        items.add("Magical melee weapon");
        items.add("Magical ranged weapon");
        items.add("Magical armour");
        items.add("Non-magical weapon");
        items.add("Non-magical armour");
        items.add("Magical ring");
        items.add("Magical amulet");
        items.add("Magical trinket");
        items.add("Magical shield");
        items.add("Magical staff");
        items.add("Magical wand");
        items.add("Magical gloves");
        items.add("Magical boots");
        items.add("Magical helmet");
        items.add("Magical arrows");
        return items;
    }

}
