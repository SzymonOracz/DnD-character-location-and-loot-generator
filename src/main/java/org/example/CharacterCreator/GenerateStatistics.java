package org.example.CharacterCreator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenerateStatistics {

    protected static final ArrayList<String> statistics = new ArrayList<>();

    public static void generateStats() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int j = 0; j < 6; j++) {
            while (true) {
                numbers.clear();
                for (int i = 0; i < 5; i++) {
                    int randomNumber = random.nextInt(6) + 1;
                    numbers.add(randomNumber);
                }
                Collections.sort(numbers);
                numbers.remove(0);
                numbers.remove(0);
                int sum = 0;
                for (int i = 0; i < 3; i++) {
                    sum += numbers.get(i);
                }
                if (sum >= 12 && sum <= 18) {
                    if(j == 0) {
                        String dex = "Dexterity: " + sum;
                        System.out.println("Dexterity: " + sum);
                        statistics.add(dex);
                    } else if (j == 1) {
                        String str = "Strength: " + sum;
                        System.out.println("Strength: " + sum);
                        statistics.add(str);
                    } else if (j == 2) {
                        String con = "Constitution: " + sum;
                        System.out.println("Constitution: " + sum);
                        statistics.add(con);
                    } else if (j == 3) {
                        String intel = "Intelligence: " + sum;
                        System.out.println("Intelligence: " + sum);
                        statistics.add(intel);
                    } else if (j == 4) {
                        String cha = "Charisma: " + sum;
                        System.out.println("Charisma: " + sum);
                        statistics.add(cha);
                    } else  {
                        String wis = "Wisdom: " + sum;
                        System.out.println("Wisdom: " + sum);
                        statistics.add(wis);
                    }
                    break;
                }
            }
        }
    }
}
