package persistence;

// Represent a  reader that reads banner from JSON data stored in file

import model.Banner;
import model.Character;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads banner from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Banner read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBanner(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses banner from JSON object and returns it
    private Banner parseBanner(JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        Banner banner = new Banner(title);
        addCharacters(banner, jsonObject);
        return banner;
    }

    // MODIFIES: wr
    // EFFECTS: parses characters from JSON object and adds them to banner
    private void addCharacters(Banner banner, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("characters");
        for (Object json : jsonArray) {
            JSONObject nextCharacter = (JSONObject) json;
            addCharacter(banner, nextCharacter);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses character from JSON object and adds it to banner
    private void addCharacter(Banner banner, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int rarity = jsonObject.getInt("rarity");
        Character c = new Character(name, rarity);
        banner.addCharacter(c);
    }




}
