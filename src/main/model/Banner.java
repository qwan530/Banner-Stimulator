package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a banner with a name and list of characters in it
public class Banner implements Writable {

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
            EventLog.getInstance().logEvent(new Event("Character " + character.getName() + " added."));
        } else {
            System.out.println("character" + character.getName() + "is already in the banner");
        }
    }

    // MODIFIES: this
    // EFFECTS: delete a character from the banner
    public void deleteCharacter(Character character) {
        characters.remove(character);
        EventLog.getInstance().logEvent(new Event("Character " + character.getName() + " deleted."));
    }

    // EFFECTS: return the title of banner
    public String getTitle() {
        return title;
    }

    // EFFECTS: return the character list of banner
    public ArrayList<Character> getCharacters() {
        return characters;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("characters", charactersToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray charactersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Character c : characters) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: pull one character from the banner
    public Character pullCharacter() {
        int min = 0;
        int max = getCharacters().size();
        int random = (int) Math.floor(Math.random() * (max - min) + min);
        Character result = getCharacters().get(random);
        EventLog.getInstance().logEvent(new Event("Pulled " + result.getName() + " from banner."));
        return result;
    }
}

