package ui;

import model.Banner;
import model.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Create a modify button
public class ModifyButton implements ActionListener {

    Banner banner;
    Stimulator stimulator;
    private static final Color background = new Color(127,151,137);

    //MODIFIES: this
    //EFFECTS: construct a modify button and add to the panel
    public ModifyButton(JPanel panel, Stimulator stimulator) {
        this.banner = stimulator.banner;
        this.stimulator = stimulator;
        JButton create = new JButton("Modify the Characters");
        create.setBounds(0,0,150,150);
        create.setHorizontalTextPosition(JButton.CENTER);
        create.addActionListener(this);


        panel.add(create);
    }

    //EFFECTS: add or delete character from the banner
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] options = {"add", "delete"};

        var selection = JOptionPane.showOptionDialog(null,"choose to add or delete character",
                "Modify Banner", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (selection == 0) {
            stimulator.addCharacter();
        } else {
            stimulator.deleteCharacter();
        }
    }

}
