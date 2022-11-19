package ui;

import model.Banner;
import ui.Stimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Create a load button
public class LoadButton implements ActionListener {

    private Stimulator stimulator;

    //MODIFIES: this
    //EFFECTS: construct a load button and add to the panel
    public LoadButton(JPanel panel, Stimulator stimulator) {
        JButton load = new JButton("Load");
        load.setBounds(0,0,150,150);
        load.setHorizontalTextPosition(JButton.CENTER);
        load.addActionListener(this);

        this.stimulator = stimulator;

        panel.add(load);
    }

    //EFFECTS: load banner from file
    @Override
    public void actionPerformed(ActionEvent e) {
        stimulator.loadBanner();
    }
}
