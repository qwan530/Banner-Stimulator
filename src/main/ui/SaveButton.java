package ui;

import model.Banner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButton implements ActionListener {


    private Banner banner;
    private Stimulator stimulator;

    public SaveButton(JPanel panel, Stimulator stimulator) {
        JButton save = new JButton("Save");
        save.setBounds(0,0,150,150);
        save.setHorizontalTextPosition(JButton.CENTER);
        save.addActionListener(this);


        this.stimulator = stimulator;
        this.banner = stimulator.banner;

        panel.add(save);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stimulator.saveBanner();
    }
}
