package ui;

import model.Banner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Create a view button
public class ViewButton implements ActionListener {

    Stimulator stimulator;

    //MODIFIES: this
    //EFFECTS: construct a view button and add to the panel
    public ViewButton(JPanel panel, Stimulator stimulator) {
        JButton view = new JButton("View characters list");
        view.setBounds(0,0,150,150);
        view.setHorizontalTextPosition(JButton.CENTER);
        view.addActionListener(this);
        this.stimulator = stimulator;

        panel.add(view);
    }

    //EFFECTS: display the characters when button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        stimulator.viewCharacter();
    }
}
