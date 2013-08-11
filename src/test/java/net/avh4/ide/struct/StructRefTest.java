package net.avh4.ide.struct;

import net.avh4.ide.struct.example.Watch;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.transaction.TransactionRequiredException;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

public class StructRefTest {
    StructRef<TestStruct> subject;
    @Mock
    private Watch<String> aWatch;
    @Mock
    private Watch<String> bWatch;
    @Mock
    private Watch<TestStruct> rootWatch;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new StructRef<TestStruct>(TestStruct.class);
        subject.watchAttribute(aWatch).a();
        subject.watchAttribute(bWatch).b();
        subject.watch(rootWatch);
    }

    @Test
    public void changingA_shouldUpdateRoot() throws Exception {
        reset(rootWatch);
        updateA();
        ArgumentCaptor<TestStruct> update = ArgumentCaptor.forClass(TestStruct.class);
        verify(rootWatch).update(update.capture());
        assertThat(update.getValue().a()).isEqualTo("new A");
    }

    @Test
    public void changingA_shouldUpdateA() throws Exception {
        updateA();
        verify(aWatch).update("new A");
    }

    private void updateA() {
        STM.transaction(new Transaction() {
            @Override
            public void run() throws TransactionRequiredException {
                subject.setAttribute("new A").a();
            }
        });
    }

}
