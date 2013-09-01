package net.avh4.ide;

import fj.data.HashMap;
import fj.data.Option;
import net.avh4.ide.commands.ExecuteProjectCommand;
import net.avh4.ide.platforms.CodeProject;

public class KeyboardHandler implements KeyboardInputSourceListener {
    private final HashMap<String, ProjectCommand> keymap;
    private HdeModel model;

    public KeyboardHandler(ExecuteProjectCommand executeCommand, HdeModel model) {
        this.model = model;
        keymap = HashMap.hashMap();
        keymap.set("⇧⌘⑩", executeCommand);
    }

    @Override
    public void sendKeyStroke(String s) {
        final Option<ProjectCommand> command = keymap.get(s);
        if (command.isSome()) command.toNull().execute(model.project());
    }
}
