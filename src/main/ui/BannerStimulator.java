package ui;

import model.Banner;
import model.Character;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Some method was taken from WorkRoomApp in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents the banner stimulator application
public class BannerStimulator {
    private static final String JSON_STORE = "./data/banner.json";
    private Banner banner;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: run the banner stimulator application
    public BannerStimulator() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runStimulator();
    }

    // MODIFIES: this
    // EFFECTS: processes
    private void runStimulator() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Good luck!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("n")) {
            modifyBanner();
        } else if (command.equals("c")) {
            createBanner();
        } else if (command.equals("p")) {
            pull();
        } else if (command.equals("s")) {
            saveBanner();
        } else if (command.equals("l")) {
            loadBanner();
        } else {
            System.out.println("Selection not valid");
        }
    }

    private void loadBanner() {
        try {
            banner = jsonReader.read();
            System.out.println("Loaded " + banner.getTitle() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // EFFECTS: saves the banner to file
    private void saveBanner() {
        try {
            jsonWriter.open();
            jsonWriter.write(banner);
            jsonWriter.close();
            System.out.println("Saved " + banner.getTitle() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // EFFECTS: pull characters from the banner and return result
    private void pull() {
        if (banner.getCharacters().size() == 0) {
            System.out.println("no character in this banner!");
        } else {
            int min = 0;
            int max = banner.getCharacters().size();
            int random = (int) Math.floor(Math.random() * (max - min) + min);
            Character result = banner.getCharacters().get(random);
            System.out.println("The result is:" + result.getName() + "," + result.getRarity());
        }
    }

    // MODIFIES: this
    // EFFECTS: create a new banner with the name inputted
    private void createBanner() {
        System.out.println("enter the name of banner");
        this.banner = new Banner(input.next());
        System.out.println("New banner" + banner.getTitle() + "is created!");
    }

    // MODIFIES: this
    // EFFECTS: display the option of modifying banner
    private void modifyBanner() {
        System.out.println("enter v to view characters in banner");
        System.out.println("enter a to add character");
        System.out.println("enter d to delete character");
        String command = input.next();
        command = command.toLowerCase();
        if (command.equals("v")) {
            viewCharacter();
        } else if (command.equals("a")) {
            addingCharacter();
        } else if (command.equals("d")) {
            deletingCharacter();
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: display the characters currently in the banner
    private void viewCharacter() {
        ArrayList<Character> characters = banner.getCharacters();
        if (characters.isEmpty()) {
            System.out.println("no characters in this banner!");
        } else {
            for (Character c : characters) {
                System.out.println(c.getName() + "," + c.getRarity());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: add a character into the banner, do not allow duplicate
    private void addingCharacter() {
        System.out.println("enter the name of character");
        Character character = new Character(input.next(), 0);
        System.out.println("enter the rarity of character (1-6)");
        character.setRarity(input.nextInt());
        banner.addCharacter(character);
        System.out.println("add more characters? (y/n)");
        String command = input.next();
        if (command.equals("y")) {
            addingCharacter();
        } else if (command.equals("n")) {
            System.out.println("return to menu");
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: delete the character from the banner
    private void deletingCharacter() {
        System.out.println("enter the name of character");
        ArrayList<Character> lst = banner.getCharacters();
        for (int i = 0; i < lst.size(); i++) {
            Character character1 = lst.get(i);
            if (character1.getName().equals(input.next())) {
                banner.deleteCharacter(character1);
                break;
            }
        }
        System.out.println("character deleted!");
        System.out.println("delete more characters? (y/n)");
        String command = input.next();
        if (command.equals("y")) {
            deletingCharacter();
        } else if (command.equals("n")) {
            displayMenu();
        } else {
            System.out.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes banner
    private void init() {
        banner = new Banner("");
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Gacha Game Banner Stimulator");
        System.out.println("enter c to create banner");
        System.out.println("enter l to load banner from file");
        System.out.println("enter s to save banner to file");
        System.out.println("enter n to modify banner");
        System.out.println("enter p to start pulling");
        System.out.println("enter q to quit");
    }
}





