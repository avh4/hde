package net.avh4.ide.platforms;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class CodePackageTest {
    private CodePackage subject;

    @Before
    public void setUp() throws Exception {
        subject = new CodePackage("com.example");
    }

    @Test
    public void shouldHaveNoClasses() throws Exception {
        assertThat(subject.getClasses()).isEmpty();
    }

    @Test
    public void addingAClass_shouldHaveThatClass() throws Exception {
        final CodeClass codeClass = new CodeClass("com.example", "HelloWorld");
        subject.addClass(codeClass);
        assertThat(subject.getClasses()).contains(codeClass);
    }
}
