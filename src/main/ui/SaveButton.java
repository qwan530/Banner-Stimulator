package ui;

import model.Banner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Create a save button
public class SaveButton implements ActionListener {

    private Stimulator stimulator;

    //MODIFIES: this
    //EFFECTS: construct a save button and add to the panel
    public SaveButton(JPanel panel, Stimulator stimulator) {
        JButton save = new JButton("Save");
        save.setBounds(0,0,150,150);
        save.setHorizontalTextPosition(JButton.CENTER);
        save.addActionListener(this);

        this.stimulator = stimulator;

        panel.add(save);
    }

    //EFFECTS: save the banner
    @Override
    public void actionPerformed(ActionEvent e) {
        stimulator.saveBanner();
    }
}
