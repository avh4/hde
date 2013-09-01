package net.avh4.tasks;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {
    @Test
    public void testQuickAddEstimates() throws Exception {
        assertEquals(10, new Task("do homework 10").getEstimate());
    }

    @Test
    public void testFail() throws Exception {
        assertEquals(false, true);
    }
}
