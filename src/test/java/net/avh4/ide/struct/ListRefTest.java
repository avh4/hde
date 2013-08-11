package net.avh4.ide.struct;

import com.google.common.collect.ImmutableList;
import net.avh4.ide.struct.example.Watch;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.transaction.TransactionRequiredException;

import static org.mockito.Mockito.verify;

public class ListRefTest {
    private ListRef<String> subject;
    @Mock
    private Watch<ImmutableList<String>> watch;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new ListRef<String>();
    }

    @Test(expected = TransactionRequiredException.class)
    public void add_notInTransaction_shouldThrow() throws Exception {
        subject.add("commons-io");
    }

    @Test
    public void add_shouldUpdateWatchers() throws Exception {
        subject.watch(watch);

        STM.transaction(new Transaction() {
            @Override
            public void run() throws TransactionRequiredException {
                subject.add("commons-io");
            }
        });
        verify(watch).update(ImmutableList.of("commons-io"));
    }

    @Test
    public void add_twice_shouldUpdateWatchers() throws Exception {
        subject.watch(watch);

        STM.transaction(new Transaction() {
            @Override
            public void run() throws TransactionRequiredException {
                subject.add("commons-io");
            }
        });
        STM.transaction(new Transaction() {
            @Override
            public void run() throws TransactionRequiredException {
                subject.add("commons-lang");
            }
        });
        verify(watch).update(ImmutableList.of("commons-io", "commons-lang"));
    }

    @Test
    public void watch_withValue_shouldUpdate() throws Exception {
        STM.transaction(new Transaction() {
            @Override
            public void run() throws TransactionRequiredException {
                subject.add("commons-io");
            }
        });

        subject.watch(watch);

        verify(watch).update(ImmutableList.of("commons-io"));
    }
}
