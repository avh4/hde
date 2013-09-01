package net.avh4.ide.ui.swing;

import net.avh4.ide.Window;
import net.avh4.ide.ui.WindowManager;

import javax.swing.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class SwingWindowManager implements WindowManager {
    @Override
    public <A> void showWindow(Window<A> window) {
        Object view = window.view();
        window.view().setActions(window.actions());
        checkNotNull(view);
        if (view instanceof JFrame) {
            JFrame jframe = (JFrame) view;
            jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jframe.pack();
            jframe.setLocationRelativeTo(null);
            jframe.setVisible(true);
        } else if (view instanceof JPanel) {
            JPanel jPanel = (JPanel) view;
            JFrame jframe = new JFrame(view.toString());
            jframe.add(jPanel);
            jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jframe.pack();
            jframe.setLocationRelativeTo(null);
            jframe.setVisible(true);
        } else {
            throw new RuntimeException("Don't know how to show view: " + view.toString()
                    + "\n  for window: " + window.toString());
        }
    }

    @Override
    public void dismissWindow(Window<?> window) {
        Object view = window.view();
        checkNotNull(view);
        if (view instanceof JFrame) {
            JFrame jframe = (JFrame) view;
            jframe.dispose();
        } else {
            throw new RuntimeException("Don't know how to dismiss view: " + view.toString()
                    + "\n  for window: " + window.toString());
        }
    }
}
