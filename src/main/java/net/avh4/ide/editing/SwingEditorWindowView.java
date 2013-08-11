package net.avh4.ide.editing;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.StringReader;

public class SwingEditorWindowView extends JPanel implements EditorWindowView {
    private RSyntaxTextArea textArea;

    public SwingEditorWindowView() {
        super(new BorderLayout());
        textArea = new RSyntaxTextArea(40, 80);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        sp.setFoldIndicatorEnabled(true);
        this.add(sp);
    }

    @Override
    public void setContent(String description, String content) throws IOException {
        textArea.read(new StringReader(content), description);
    }

    @Override
    public void setActions(Void actions) {
    }

    @Override
    public Void actions() {
        return null;
    }
}
