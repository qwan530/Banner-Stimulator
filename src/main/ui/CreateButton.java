package ui;

import model.Banner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateButton implements ActionListener {

    private Banner banner;
    private Stimulator stimulator;

    public CreateButton(JPanel panel, Stimulator stimulator) {
        JButton create = new JButton("Create a Banner");
        create.setBounds(0,0,150,150);
        create.setHorizontalTextPosition(JButton.CENTER);
        create.addActionListener(this);
        this.stimulator = stimulator;
        this.banner = stimulator.banner;


        panel.add(create);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(stimulator.banner == null)) {
            JOptionPane.showMessageDialog(null,"You have already created a banner!");
        } else {
            String name = JOptionPane.showInputDialog("enter the name of banner");
            stimulator.createBanner(name);
            JOptionPane.showMessageDialog(null, "New banner" + name + " is created!",
                    "Create Banner", JOptionPane.PLAIN_MESSAGE);
        }
    }
}