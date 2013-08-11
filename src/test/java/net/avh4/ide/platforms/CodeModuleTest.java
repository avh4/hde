package net.avh4.ide.platforms;

import net.avh4.test.junit.Nested;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(Nested.class)
public class CodeModuleTest {

    private CodeModule subject;

    @Before
    public void setUp() throws Exception {
        subject = new CodeModule();
    }

    public class Empty {
        @Test
        public void shouldHaveNoClasses() throws Exception {
            assertThat(subject.classes()).isEmpty();
        }
    }

    public class WithSingleClass {
        @Before
        public void setUp() throws Exception {
            subject = subject.addClass("com.example", "HelloWorld", null);
        }

        @Test
        public void shouldHaveClassName() throws Exception {
            assertThat(subject.classes().head().name()).isEqualTo("HelloWorld");
        }

        @Test
        public void shouldHaveClassPackage() throws Exception {
            assertThat(subject.classes().head().packageName()).isEqualTo("com.example");
        }

        @Test
        public void shouldHaveClassSource() throws Exception {
//            assertThat(subject.classes().head().name()).isEqualTo("HelloWorld");
        }
    }
}
