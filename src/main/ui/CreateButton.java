package ui;

import model.Banner;
import ui.Stimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Create a create button
public class CreateButton implements ActionListener {

    private Stimulator stimulator;

    //MODIFIES: this
    //EFFECTS: construct a create button and add to the panel
    public CreateButton(JPanel panel, Stimulator stimulator) {
        JButton create = new JButton("Create a Banner");
        create.setBounds(0,0,150,150);
        create.setHorizontalTextPosition(JButton.CENTER);
        create.addActionListener(this);
        this.stimulator = stimulator;

        panel.add(create);
    }

    //EFFECTS: create a new banner
    @Override
    public void actionPerformed(ActionEvent e) {
        stimulator.createBanner();
    }
}
