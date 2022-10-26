package persistence;

import model.Banner;
import model.Character;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCharacter(String name, int rarity, Character character) {
        assertEquals(name, character.getName());
        assertEquals(rarity, character.getRarity());
    }
}
