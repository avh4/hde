package net.avh4.ide.platforms;

import net.avh4.test.junit.Nested;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(Nested.class)
public class CodeProjectTest {

    private CodeProject subject;
    @Mock private CodeModule module;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new CodeProject();
    }

    public class Empty {
        @Test
        public void shouldHaveNoModules() throws Exception {
            assertThat(subject.modules()).isEmpty();
        }
    }

    public class WithSingleModule {
        @Before
        public void setUp() throws Exception {
            subject = subject.addModule(module);
        }

        @Test
        public void shouldHaveModuleName() throws Exception {
            assertThat(subject.modules().head()).isSameAs(module);
        }
    }
}
