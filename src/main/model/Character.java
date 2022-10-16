package model;

public class Character {

    private String name;
    private int rarity;

    // REQUIRES: 6>= rarity >= 1
    // EFFECTS: create a character with a name and a rarity
    public Character(String name, int rarity) {
        this.name = name;
        this.rarity = rarity;
    }

    // REQUIRES: 6>= rarity >= 1
    // MODIFIES: this
    // EFFECTS: change the rarity of the character
    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    // EFFECTS: return the name of the character
    public String getName() {
        return name;
    }

    // EFFECTS: return the rarity of the character
    public int getRarity() {
        return rarity;
    }
}
