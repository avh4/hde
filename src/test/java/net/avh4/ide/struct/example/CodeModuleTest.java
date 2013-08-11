package net.avh4.ide.struct.example;

import net.avh4.ide.struct.STM;
import net.avh4.ide.struct.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.transaction.TransactionRequiredException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class CodeModuleTest {
    private CodeModule subject;
    @Mock
    private Watch<String> nameWatch;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new CodeModule();
    }

    @Test
    public void watch_withNoValue_shouldNotUpdate() throws Exception {
        subject.watchName(nameWatch);
        verifyZeroInteractions(nameWatch);
    }

    @Test
    public void set_shouldUpdateWatchers() throws Exception {
        subject.watchName(nameWatch);

        STM.transaction(new Transaction() {
            @Override
            public void run() throws TransactionRequiredException {
                subject.setName("commons-io");
            }
        });
        verify(nameWatch).update("commons-io");
    }

    @Test
    public void watch_withValue_shouldUpdate() throws Exception {
        STM.transaction(new Transaction() {
            @Override
            public void run() throws TransactionRequiredException {
                subject.setName("commons-io");
            }
        });

        subject.watchName(nameWatch);

        verify(nameWatch).update("commons-io");
    }

    @Test(expected = TransactionRequiredException.class)
    public void setName_notInTransaction_shouldThrow() throws Exception {
        subject.setName("commons-io");
    }
}
