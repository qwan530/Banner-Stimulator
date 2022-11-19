package ui;

import model.Banner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PullButton implements ActionListener {

    private Banner banner;
    private Stimulator stimulator;

    public PullButton(JPanel panel, Stimulator stimulator) {
        JButton pull = new JButton("Pull from the Banner");
        pull.setBounds(0,0,150,150);
        pull.setHorizontalTextPosition(JButton.CENTER);
        pull.addActionListener(this);

        this.stimulator = stimulator;
        this.banner = stimulator.banner;

        panel.add(pull);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stimulator.pull();
    }
}
