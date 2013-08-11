package net.avh4.ide.editing;

import com.google.common.collect.ImmutableSet;
import net.avh4.ide.Function;
import net.avh4.ide.Window;
import net.avh4.ide.WindowView;
import net.avh4.ide.platforms.CodeClass;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditorWindow implements Window<Void> {
    private final CodeClass codeClass;
    private final EditorWindowView view;

    public EditorWindow(CodeClass codeClass, EditorWindowView view) {
        this.codeClass = checkNotNull(codeClass);
        this.view = checkNotNull(view);

        try {
            view.setContent(codeClass.name(), codeClass.getSource());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImmutableSet<Function> functions() {
        return ImmutableSet.of(Function.EDITING);
    }

    @Override
    public WindowView<Void> view() {
        return view;
    }

    public String getContents() {
        return codeClass.getSource();
    }
}
