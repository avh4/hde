package net.avh4.ide;

import net.avh4.ide.ui.WindowManager;

public class Application {

    private final WindowManager windowManager;
    private final WelcomeWindow welcomeWindow;
    private KeyboardInputSource keyboardInputSource;
    private KeyboardInputSourceListener keyboardHandler;

    public Application(WindowManager windowManager, WelcomeWindow welcomeWindow,
                       KeyboardInputSource keyboardInputSource, KeyboardHandler keyboardHandler) {
        this.windowManager = windowManager;
        this.welcomeWindow = welcomeWindow;
        this.keyboardInputSource = keyboardInputSource;
        this.keyboardHandler = keyboardHandler;
    }

    public void start() {
        keyboardInputSource.addKeyboardInputSourceListener(keyboardHandler);
        windowManager.showWindow(welcomeWindow);
        keyboardInputSource.start();
    }
}
