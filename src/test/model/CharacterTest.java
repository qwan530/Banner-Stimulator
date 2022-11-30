package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CharacterTest {

    private Character testCharacter;

    @BeforeEach
    void runBefore() {
        testCharacter = new Character("Saber", "5");
    }

    @Test
    void testConstructor() {
        assertEquals("Saber", testCharacter.getName());
        assertEquals(5, testCharacter.getRarity());
    }

    @Test
    void testSetRarity() {
        testCharacter.setRarity("4");
        assertEquals("4", testCharacter.getRarity());
    }

    @Test
    void testGetName() {
        assertEquals("Saber", testCharacter.getName());
    }

    @Test
    void testGetRarity() {
        assertEquals(5, testCharacter.getRarity());
    }



}
