package ui;

import model.Banner;
import model.Character;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Stimulator extends JFrame {
        static final String JSON_STORE = "./data/banner.json";
        private static final Color background = new Color(127,151,137);
        private JFrame frame;
        public Banner banner;
        private JsonWriter jsonWriter;
        private JsonReader jsonReader;

    public Stimulator() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame("Banner Stimulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,600);
        frame.getContentPane().setBackground(background);

        addTitle();
        addSubtitle();
        addMenu1();
        addMenu2();

        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void addSubtitle() {
        JPanel subtitle = new JPanel();
        subtitle.setBounds(0,110,900,69);
        subtitle.setBackground(background);
        JLabel label = new JLabel();
        label.setText("Create a banner and stimulate your results");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Verdana",1,17));
        subtitle.add(label);

        frame.add(subtitle);
    }


    void addMenu1() {
        JPanel menu = new JPanel();
        menu.setBackground(background);
        menu.setBounds(370,200, 160,200);
        CreateButton createButton = new CreateButton(menu, this);
        ModifyButton modifyButton = new ModifyButton(menu, this);
        ViewButton viewButton = new ViewButton(menu, this);
        PullButton pullButton = new PullButton(menu, this);

        frame.add(menu);
    }

    void addMenu2() {
        JPanel menu = new JPanel();
        menu.setBackground(background);
        menu.setBounds(370,460, 160,200);

        SaveButton saveButton = new SaveButton(menu, this);
        LoadButton loadButton = new LoadButton(menu,this);

        frame.add(menu);
    }


    // EFFECTS: add title to the frame
    private void addTitle() {
        JPanel title = new JPanel();
        title.setBounds(0,50,900,69);
        title.setBackground(background);
        ImageIcon t = new ImageIcon("Title image.png");
        JLabel label = new JLabel();
        label.setIcon(t);
        title.add(label);

        frame.add(title);
    }

    void addCharacter() {
        String name = JOptionPane.showInputDialog("enter the name of character");
        Character character = new Character(name, 0);
        int rarity = Integer.parseInt(JOptionPane.showInputDialog("enter the rarity of character"));
        character.setRarity(rarity);
        banner.addCharacter(character);
        JOptionPane.showMessageDialog(null, "Character added!");
        var selection = JOptionPane.showConfirmDialog(null,"add more characters?");
        if (selection == 0) {
            addCharacter();
        }
    }


    public void createBanner(String name) {
        this.banner = new Banner(name);
    }

    public void deleteCharacter() {
        String name = JOptionPane.showInputDialog("enter the name of character");
        ArrayList<Character> lst = banner.getCharacters();
        for (int i = 0; i < lst.size(); i++) {
            Character character1 = lst.get(i);
            if (character1.getName().equals(name)) {
                banner.deleteCharacter(character1);
                break;
            }
        }
        JOptionPane.showMessageDialog(null, "Character deleted!");
        var selection = JOptionPane.showConfirmDialog(null,"delete more characters?");
        if (selection == 0) {
            deleteCharacter();
        }
    }

    public void viewCharacter() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Rarity");

        ArrayList<Character> characters = banner.getCharacters();
        for (Character c : characters) {
            tableModel.addRow(new Object[] {c.getName(), c.getRarity()});
        }
        JTable table = new JTable(tableModel);
        JOptionPane.showMessageDialog(null, table, "Characters", JOptionPane.PLAIN_MESSAGE);

    }

    public void saveBanner() {
        if (banner == null) {
            JOptionPane.showMessageDialog(null, "No banner created yet!");
        } else {
            try {
                jsonWriter.open();
                jsonWriter.write(banner);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null,
                        "Saved " + banner.getTitle() + " to " + JSON_STORE);

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Unable to write to file: " + JSON_STORE);
            }

        }
    }

    public void loadBanner() {
        try {
            banner = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded " + banner.getTitle() + " from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file: " + JSON_STORE);
        }
    }

    public void pull() {
        if (banner.getCharacters().size() == 0) {
            JOptionPane.showMessageDialog(null,"no character in this banner!");
        } else {
            int min = 0;
            int max = banner.getCharacters().size();
            int random = (int) Math.floor(Math.random() * (max - min) + min);
            Character result = banner.getCharacters().get(random);
            JOptionPane.showMessageDialog(null,
                    "The result is:" + result.getName() + "," + result.getRarity());
        }
    }
}

