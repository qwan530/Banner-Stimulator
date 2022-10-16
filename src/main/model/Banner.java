package model;

import java.util.ArrayList;

// Represents a banner with a name and list of characters in it
public class Banner {

    private String title;
    private ArrayList<Character> characters;

    // EFFECTS: create a banner with a name and no character in it
    public Banner(String title) {
        this.title = title;
        this.characters = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a character into the banner
    public void addCharacter(Character character) {

        if (!this.characters.contains(character)) {
            this.characters.add(character);
            System.out.println("character added!");
        } else {
            System.out.println("character" + character.getName() + "is already in the banner");
        }
    }

    // MODIFIES: this
    // EFFECTS: delete a character from the banner
    public void deleteCharacter(Character character) {
        characters.remove(character);
    }

    // EFFECTS: return the title of banner
    public String getTitle() {
        return title;
    }

    // EFFECTS: return the character list of banner
    public ArrayList<Character> getCharacters() {
        return characters;
    }
}
