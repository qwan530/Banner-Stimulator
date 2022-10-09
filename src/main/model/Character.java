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

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }
}
