package persistence;

import model.Banner;
import model.Character;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Method was taken from JsonReaderTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Banner banner = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBanner() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBanner.json");
        try {
            Banner banner = reader.read();
            assertEquals("My banner", banner.getTitle());
            assertEquals(0, banner.getCharacters().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBanner() {
        JsonReader reader
                = new JsonReader("./data/testReaderGeneralBanner.json");
        try {
            Banner banner = reader.read();
            assertEquals("My banner", banner.getTitle());
            List<Character> characters = banner.getCharacters();
            assertEquals(2, characters.size());
            checkCharacter("a", 1, characters.get(0));
            checkCharacter("b", 2, characters.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
