package net.avh4.ide.editing;

import net.avh4.ide.HdeModel;
import net.avh4.ide.platforms.CodeClass;
import org.picocontainer.PicoContainer;

public class EditorWindowProvider {

    private final PicoContainer pico;

    public EditorWindowProvider(PicoContainer pico) {
        this.pico = pico;
    }

    public EditorWindow get(CodeClass codeClass) {
        final EditorWindowView view = pico.getComponent(EditorWindowView.class);
        final HdeModel model = pico.getComponent(HdeModel.class);
        return new EditorWindow(codeClass, view, model);
    }
}
