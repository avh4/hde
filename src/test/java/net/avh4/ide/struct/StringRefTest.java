package net.avh4.ide.struct;

import net.avh4.ide.struct.example.Watch;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.transaction.TransactionRequiredException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class StringRefTest {
    private StringRef subject;
    @Mock
    private Watch<String> watch;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new StringRef();
    }

    @Test
    public void watch_withNoValue_shouldNotUpdate() throws Exception {
        subject.watch(watch);
        verifyZeroInteractions(watch);
    }

    @Test(expected = TransactionRequiredException.class)
    public void set_notInTransaction_shouldThrow() throws Exception {
        subject.set("commons-io");
    }

    @Test
    public void set_shouldUpdateWatchers() throws Exception {
        subject.watch(watch);

        STM.transaction(new Transaction() {
            @Override
            public void run() throws TransactionRequiredException {
                subject.set("commons-io");
            }
        });
        verify(watch).update("commons-io");
    }

    @Test
    public void watch_withValue_shouldUpdate() throws Exception {
        STM.transaction(new Transaction() {
            @Override
            public void run() throws TransactionRequiredException {
                subject.set("commons-io");
            }
        });

        subject.watch(watch);

        verify(watch).update("commons-io");
    }
}
