package ui;

import model.Banner;
import model.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewButton implements ActionListener {
    Banner banner;
    Stimulator stimulator;

    public ViewButton(JPanel panel, Stimulator stimulator) {
        JButton view = new JButton("View characters list");
        view.setBounds(0,0,150,150);
        view.setHorizontalTextPosition(JButton.CENTER);
        view.addActionListener(this);
        this.stimulator = stimulator;
        this.banner = stimulator.banner;


        panel.add(view);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stimulator.viewCharacter();
    }
}
