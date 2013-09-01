package net.avh4.ide.editing;

import net.avh4.ide.HdeModel;
import net.avh4.ide.platforms.CodeClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class EditorWindowTest {
    private EditorWindow subject;
    @Mock private CodeClass codeClass;
    @Mock private EditorWindowView view;
    @Mock private HdeModel model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(codeClass.name()).toReturn("FancySort");
        stub(codeClass.source()).toReturn("class FancySort { Object sort() { return null; } }");
        subject = new EditorWindow(codeClass, view, model);
    }

    @Test
    public void shouldShowClassSource() throws Exception {
        verify(view).setContent(codeClass.name(), codeClass.source());
    }

    @Test
    public void changeSourceText_shouldUpdateModel() throws Exception {
        subject.actions().changeSourceText("package;");
        verify(model).replaceClassSource("FancySort", "package;");
    }
}
