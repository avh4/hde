package net.avh4.ide.editing;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.IOException;
import java.io.StringReader;

public class SwingEditorWindowView extends JPanel implements EditorWindowView {
    private RSyntaxTextArea textArea;
    private EditorWindow.Actions actions;

    public SwingEditorWindowView() {
        super(new BorderLayout());
        textArea = new RSyntaxTextArea(40, 80);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        sp.setFoldIndicatorEnabled(true);
        this.add(sp);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fireChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fireChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fireChange();
            }
        });
    }

    private void fireChange() {
        if (actions == null) return;
        actions.changeSourceText(textArea.getText());
    }

    @Override
    public void setContent(String description, String content) {
        try {
            textArea.read(new StringReader(content), description);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't set content of RSyntaxTextArea", e);
        }
    }

    @Override
    public void setActions(EditorWindow.Actions actions) {
        this.actions = actions;
    }
}
