package net.avh4.ide;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class KeyboardInputSource {
    private KeyboardInputSourceListener listener;

    public void addKeyboardInputSourceListener(KeyboardInputSourceListener listener) {
        checkNotNull(listener);
        if (this.listener != null) throw new RuntimeException("Multiple KeyboardInputSourceListeners not implemented");
        this.listener = listener;
    }

    protected void sendKeyStroke(String s) {
        if (listener == null) throw new RuntimeException("No KeyboardInputSource listeners have been added");
        listener.sendKeyStroke(s);
    }

    public abstract void start();
}
