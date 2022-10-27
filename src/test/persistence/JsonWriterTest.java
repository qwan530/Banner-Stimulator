package persistence;

import model.Character;
import model.Banner;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Method was taken from JsonWriterTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Banner banner = new Banner("My banner");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Banner banner = new Banner("My banner");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBanner.json");
            writer.open();
            writer.write(banner);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBanner.json");
            banner = reader.read();
            assertEquals("My banner", banner.getTitle());
            assertEquals(0, banner.getCharacters().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Banner banner = new Banner("My banner");
            banner.addCharacter(new Character("a", 1));
            banner.addCharacter(new Character("b", 2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBanner.json");
            writer.open();
            writer.write(banner);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBanner.json");
            banner = reader.read();
            assertEquals("My banner", banner.getTitle());
            List<Character> characters = banner.getCharacters();
            assertEquals(2, characters.size());
            checkCharacter("a", 1, characters.get(0));
            checkCharacter("b", 2, characters.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}