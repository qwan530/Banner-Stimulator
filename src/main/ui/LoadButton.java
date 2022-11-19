package ui;

import model.Banner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadButton implements ActionListener {

    private Banner banner;
    private Stimulator stimulator;

    public LoadButton(JPanel panel, Stimulator stimulator) {
        JButton load = new JButton("Load");
        load.setBounds(0,0,150,150);
        load.setHorizontalTextPosition(JButton.CENTER);
        load.addActionListener(this);

        this.stimulator = stimulator;
        this.banner = stimulator.banner;

        panel.add(load);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stimulator.loadBanner();
    }
}
