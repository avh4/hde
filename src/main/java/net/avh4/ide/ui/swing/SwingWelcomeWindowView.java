package net.avh4.ide.ui.swing;

import net.avh4.ide.WelcomeWindow;
import net.avh4.ide.WelcomeWindowView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingWelcomeWindowView extends JFrame implements WelcomeWindowView {
    private WelcomeWindow.Actions actions;

    public SwingWelcomeWindowView() {
        super("Welcome");
        setLayout(new GridLayout(0, 2));
        final JButton comp = new JButton("New Project");
        comp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actions.tapNewAndroidProject();
            }
        });
        add(comp);
        final JButton comp1 = new JButton("Open Project");
        comp1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actions.tapOpenProject();
            }
        });
        add(comp1);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 200);
    }

    @Override
    public void setActions(WelcomeWindow.Actions actions) {
        this.actions = actions;
    }
}
