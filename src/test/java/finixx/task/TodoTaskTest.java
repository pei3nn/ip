package finixx.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    @Test
    public void testToSaveFormatString_doneTask() {
        TodoTask t = new TodoTask("read book", true, "");
        assertEquals("T | 1 | read book | ", t.toSaveFormatString());
    }

    @Test
    public void testToSaveFormatString_undoneTask() {
        TodoTask t = new TodoTask("read book", false, "Harry Potter");
        assertEquals("T | 0 | read book | Harry Potter", t.toSaveFormatString());
    }
}
