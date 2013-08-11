package net.avh4.ide.struct;

import com.google.common.collect.ImmutableList;
import net.avh4.ide.struct.example.Watch;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.transaction.TransactionRequiredException;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ListOfStructsTest {
    private ListRef<TestStruct> subject;
    @Mock
    private Watch<ImmutableList<TestStruct>> watch;
    @Captor
    ArgumentCaptor<ImmutableList<TestStruct>> update;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new ListRef<TestStruct>();
    }

    @Test
    public void test() throws Exception {
        subject.watch(watch);

        STM.transaction(new Transaction() {
            @Override
            public void run() throws TransactionRequiredException {
//                StructRef<TestStruct> o = subject.add();
//                o.setAttribute("10").a();
//                o.setAttribute("20").b();
            }
        });

        verify(watch).update(update.capture());
        assertThat(update.getValue().get(0).a()).isEqualTo("10");
        assertThat(update.getValue().get(0).b()).isEqualTo("20");
    }
}
