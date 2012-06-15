package net.avh4.sandbox;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class SandboxTest {

    @Test
    public void shouldInitiallyBeEmpty() {
        assertThat(new File(".").list().length, not(0));

        final Sandbox subject = new Sandbox();

        assertThat(subject.newFile(".").list().length, is(0));
    }

}
