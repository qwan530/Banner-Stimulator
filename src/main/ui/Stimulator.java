package ui;

import model.Banner;
import model.Character;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Represents the banner stimulator application
public class Stimulator extends JFrame {
    private JFrame frame;
    private Banner banner;

    private static final Color background = new Color(127,151,137);
    private ImageIcon pullIcon = new ImageIcon("img.png");
    private ImageIcon saveIcon = new ImageIcon("img_1.png");
    private ImageIcon addIcon = new ImageIcon("img_2.png");

    static final String JSON_STORE = "./data/banner.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: run the banner stimulator application
    public Stimulator() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        frame = new JFrame("Banner Stimulator");
        frame.setSize(900,600);
        frame.getContentPane().setBackground(background);

        addMenu();

        frame.setLayout(null);
        frame.setVisible(true);

        setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog log = EventLog.getInstance();
                for (Event event : log) {
                    System.out.println(event);
                }
                e.getWindow().dispose();
            }
        }
        );
    }

    //EFFECTS: add all the components to the frame
    private void addMenu() {
        addTitle();
        addSubtitle();
        addMenu1();
        addMenu2();
    }


    //EFFECTS: add the subtitle to the frame
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


    //EFFECTS: add create, modify, view, pull button to the frame
    void addMenu1() {
        JPanel menu = new JPanel();
        menu.setBackground(background);
        menu.setBounds(370,200, 170,200);
        CreateButton createButton = new CreateButton(menu, this);
        ModifyButton modifyButton = new ModifyButton(menu, this);
        ViewButton viewButton = new ViewButton(menu, this);
        PullButton pullButton = new PullButton(menu, this);

        frame.add(menu);
    }


    //EFFECTS: add save and load button to the frame
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


    // MODIFIES: this
    // EFFECTS: add a character into the banner, do not allow duplicate
    void addCharacter() {
        if (banner == null) {
            JOptionPane.showMessageDialog(null, "No banner created yet!");
        } else {
            String name = JOptionPane.showInputDialog("Enter the name of character");
            Character character = new Character(name, "0");
            String rarity = (JOptionPane.showInputDialog(null,
                    "Enter the rarity of character (1-5)", ""));
            character.setRarity(rarity);
            banner.addCharacter(character);
            JOptionPane.showMessageDialog(null, "Character added!", "Add charcacter",
                    JOptionPane.PLAIN_MESSAGE, addIcon);
            int selection = JOptionPane.showConfirmDialog(null, "Add more characters?");
            if (selection == 0) {
                addCharacter();
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: create a new banner with the name inputted
    public void createBanner() {
        if (!(banner == null)) {
            JOptionPane.showMessageDialog(null,"You have already created a banner!");
        } else {
            String name = JOptionPane.showInputDialog("enter the name of banner");
            this.banner = new Banner(name);
            JOptionPane.showMessageDialog(null, "New banner" + name + " is created!",
                    "Create Banner", JOptionPane.PLAIN_MESSAGE);
        }
    }


    // MODIFIES: this
    // EFFECTS: show the deleting window
    public void deleteCharacter() {
        if (banner == null) {
            JOptionPane.showMessageDialog(null, "No banner created yet!");
        } else {
            deleting();
            int selection = JOptionPane.showConfirmDialog(null, "Delete other characters?");
            if (selection == 0) {
                deleteCharacter();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: delete the character from the banner
    public void deleting() {
        String name = JOptionPane.showInputDialog("Enter the name of character");
        Boolean contain = false;
        ArrayList<Character> lst = banner.getCharacters();
        for (int i = 0; i < lst.size(); i++) {
            Character character1 = lst.get(i);
            if (character1.getName().equals(name)) {
                banner.deleteCharacter(character1);
                contain = true;
                break;
            }
        }
        if (contain) {
            JOptionPane.showMessageDialog(null, "Character deleted!");
        } else {
            JOptionPane.showMessageDialog(null, "Character does not exist!");
        }
    }


    // EFFECTS: display the characters currently in the banner
    public void viewCharacter() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Rarity");

        if (banner == null) {
            JOptionPane.showMessageDialog(null, "No banner created yet!");
        } else {
            ArrayList<Character> characters = banner.getCharacters();
            if (characters.size() == 0) {
                JOptionPane.showMessageDialog(null, "No characters in the banner",
                        "Characters", JOptionPane.ERROR_MESSAGE);
            }
            for (Character c : characters) {
                tableModel.addRow(new Object[]{c.getName(), c.getRarity()});
            }
            JTable table = new JTable(tableModel);
            JOptionPane.showMessageDialog(null, table, "Characters", JOptionPane.PLAIN_MESSAGE);
        }
    }


    // EFFECTS: saves the banner to file
    public void saveBanner() {
        if (banner == null) {
            JOptionPane.showMessageDialog(null, "No banner created yet!", "Save",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                jsonWriter.open();
                jsonWriter.write(banner);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null,
                        "Saved " + banner.getTitle() + " to " + JSON_STORE, "Save",
                        JOptionPane.PLAIN_MESSAGE, saveIcon);

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Unable to write to file: " + JSON_STORE, "Save", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    // EFFECTS: load the banner from file
    public void loadBanner() {
        try {
            banner = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded " + banner.getTitle() + " from " + JSON_STORE, "Load",
                    JOptionPane.PLAIN_MESSAGE, saveIcon);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file: " + JSON_STORE, "Load", JOptionPane.ERROR_MESSAGE);
        }
    }


    // EFFECTS: pull characters from the banner and return result
    public void pull() {
        if (banner == null) {
            JOptionPane.showMessageDialog(null, "No banner created yet!");
        } else {
            if (banner.getCharacters().size() == 0) {
                JOptionPane.showMessageDialog(null, "no character in this banner!", "Pull",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                Character result = banner.pullCharacter();
                JOptionPane.showMessageDialog(null,
                        "The result is:" + result.getName() + "," + result.getRarity(), "Pull",
                        JOptionPane.PLAIN_MESSAGE, pullIcon);
            }
        }
    }

    public void modifyBanner() {
        if (banner == null) {
            JOptionPane.showMessageDialog(null, "No banner created yet!");
        } else {
            String[] options = {"add", "delete"};

            int selection = JOptionPane.showOptionDialog(null, "choose to add or delete character",
                    "Modify Banner", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);

            if (selection == 0) {
                addCharacter();
            } else {
                deleteCharacter();
            }
        }
    }
}

