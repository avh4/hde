package net.avh4.ide.editing;

import com.google.common.collect.ImmutableSet;
import net.avh4.ide.Function;
import net.avh4.ide.HdeModel;
import net.avh4.ide.Window;
import net.avh4.ide.WindowView;
import net.avh4.ide.platforms.CodeClass;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditorWindow implements Window<EditorWindow.Actions> {
    private final CodeClass codeClass;
    private final EditorWindowView view;
    private HdeModel model;

    public interface Actions {
        void changeSourceText(String newSource);
    }

    public EditorWindow(CodeClass codeClass, EditorWindowView view, HdeModel model) {
        this.model = model;
        this.codeClass = checkNotNull(codeClass);
        this.view = checkNotNull(view);

        view.setContent(codeClass.name(), codeClass.source());
    }

    @Override
    public ImmutableSet<Function> functions() {
        return ImmutableSet.of(Function.EDITING);
    }

    @Override
    public WindowView<Actions> view() {
        return view;
    }

    @Override
    public Actions actions() {
        return new Actions() {
            @Override
            public void changeSourceText(String newSource) {
                model.replaceClassSource(codeClass.name(), newSource);
            }
        };
    }

    public String getContents() {
        return codeClass.source();
    }
}
