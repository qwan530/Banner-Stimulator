package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BannerTest {
    private Banner banner;
    private Character c1;
    private Character c2;

    @BeforeEach
    void runBefore() {
        banner = new Banner("testbanner");
        c1 = new Character("a", "1");
        c2 = new Character("b", "2");
    }

    @Test
    void testConstructor() {
        assertEquals("testbanner", banner.getTitle());
        assertEquals(0, banner.getCharacters().size());
    }

    @Test
    void testAddCharacter() {
        banner.addCharacter(c1);
        ArrayList<Character> characters = banner.getCharacters();
        assertTrue(characters.contains(c1));
        assertFalse(characters.contains(c2));
        assertEquals(1, characters.size());
    }

    @Test
    void testAddCharacterTwice() {
        banner.addCharacter(c1);
        banner.addCharacter(c1);
        ArrayList<Character> characters = banner.getCharacters();
        assertTrue(characters.contains(c1));
        assertFalse(characters.contains(c2));
        assertEquals(1, characters.size());
    }

    @Test
    void testDeleteCharacter() {
        banner.addCharacter(c1);
        banner.addCharacter(c2);
        ArrayList<Character> characters = banner.getCharacters();
        banner.deleteCharacter(c1);
        assertFalse(characters.contains(c1));
        assertTrue(characters.contains(c2));
        assertEquals(1, characters.size());
    }

    @Test
    void testGetTitle() {
        assertEquals("testbanner",banner.getTitle());
    }

    @Test
    void testGetCharacter() {
        ArrayList<Character> characters = banner.getCharacters();
        banner.addCharacter(c1);
        banner.addCharacter(c2);
        assertEquals(c1, characters.get(0));
        assertEquals(c2, characters.get(1));
        assertEquals(2, characters.size());
    }
}