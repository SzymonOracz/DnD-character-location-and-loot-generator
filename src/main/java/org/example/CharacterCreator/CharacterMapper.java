package org.example.CharacterCreator;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CharacterMapper {

    static Scanner scanner = new Scanner(System.in);

    public static void parseRace(HttpResponse<String> responseBody) {
        JSONObject result = new JSONObject(responseBody.body());
        JSONArray resulArray = result.getJSONArray("results");
        for (int i = 0; i < resulArray.length(); i++) {
            System.out.println(resulArray.getJSONObject(i).getString("name"));
        }
    }
    public static void parseClassOptions(HttpResponse<String> responseBody) {
        JSONObject result = new JSONObject(responseBody.body());
        JSONArray resulArray = result.getJSONArray("results");
        for (int i = 0; i < resulArray.length(); i++) {
            System.out.println(resulArray.getJSONObject(i).getString("name"));
        }
    }

    public static void parseAlignments(HttpResponse<String> responseBody) {
        JSONObject result = new JSONObject(responseBody.body());
        JSONArray resulArray = result.getJSONArray("results");
        for (int i = 0; i < resulArray.length(); i++) {
            System.out.println(resulArray.getJSONObject(i).getString("name"));
        }
    }

    public static void parseClass(HttpResponse<String> responseBody) {
        JSONObject result = new JSONObject(responseBody.body());
        System.out.println("Now we will choose your proficiencies.\n");
        JSONArray proficiencyChoices = result.getJSONArray("proficiency_choices");
        for (int i = 0; i < 1; i++) {
            JSONObject proficiency = proficiencyChoices.getJSONObject(i);
            if (proficiency.has("choose")) {
                int choose = proficiency.getInt("choose");
                String desc = proficiency.getString("desc");
                System.out.println(desc);
                int validInputs = 0;
                while (validInputs < choose) {
                    String addProficiency = scanner.nextLine();
                    if (addProficiency.length() > 4 && addProficiency.matches("[a-zA-Z\\s']+")) {
                        if (Character.checkProfs(addProficiency)) {
                            Character.addProf(addProficiency);
                            validInputs++;
                        }
                    } else {
                        System.out.println("This is not a proficiency");
                    }
                }
            }
        }
        JSONArray savingThrows = result.getJSONArray("saving_throws");
        for (int i = 0; i < savingThrows.length(); i++) {
            JSONObject savingThrow = savingThrows.getJSONObject(i);
            String name = savingThrow.getString("name");
            Character.addST(name);
        }

        JSONArray startingEquipment = result.getJSONArray("starting_equipment");
        for (int i = 0; i < startingEquipment.length(); i++) {
            JSONObject equipment = startingEquipment.getJSONObject(i).getJSONObject("equipment");
            String name = equipment.getString("name");
            Character.addEQ(name);
        }

        JSONArray startingEquipmentOptions = result.getJSONArray("starting_equipment_options");
        for (int i = 0; i < startingEquipmentOptions.length(); i++) {
            JSONObject option = startingEquipmentOptions.getJSONObject(i);
            String from = option.getString("desc");
            for(int j =0; j < 1; j++) {
                if (from.contains("(")) {
                    System.out.println(from);
                    int validInputs = 0;
                    while (validInputs < 1) {
                        String addEquipment = scanner.nextLine();
                        if (addEquipment.matches("[\\w\\s’]*") && addEquipment.length() > 2) {
                            Character.addEQ(addEquipment);
                            validInputs++;
                        } else {
                            System.out.println("This is not equipment");
                        }
                    }
            } else {
                    Character.addEQ(from);
                }
            }
        }
    }
}
