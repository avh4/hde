package net.avh4.ide.ui.swing;

import fj.Ord;
import fj.data.TreeMap;
import net.avh4.ide.KeyboardInputSource;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SwingKeyboardInputSource extends KeyboardInputSource implements KeyEventDispatcher {
    private static final TreeMap<Integer, String> map = TreeMap.<Integer, String>empty(Ord.intOrd)
            .set(KeyEvent.VK_F10, "⑩");

    @Override
    public void start() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.isActionKey()) {
            String keystroke = "";
            if (e.isControlDown()) keystroke += "⌃";
            if (e.isAltDown()) keystroke += "⌥";
            if (e.isShiftDown()) keystroke += "⇧";
            if (e.isMetaDown()) keystroke += "⌘";
            keystroke += map.get(e.getKeyCode()).orSome(e.getKeyChar() + "");
            sendKeyStroke(keystroke);
        }
        return false;
    }
}
