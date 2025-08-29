package baymaxx.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testMarkAsDone_state() {
        TodoTask t = new TodoTask("read book", false);
        t.markAsDone();
        assertEquals(true, t.isDone());
    }

    @Test
    public void testMarkAsDone_string() {
        TodoTask t = new TodoTask("read book", false);
        t.markAsDone();
        assertEquals("[T][X] read book", t.toString());
    }

    @Test
    public void testMarkAsNotDone_state() {
        DeadlineTask d = new DeadlineTask("submit assignment ", true, "by 2025-10-30");
        d.markAsNotDone();
        assertEquals(false, d.isDone());
    }

    @Test
    public void testMarkAsNotDone_string() {
        DeadlineTask d = new DeadlineTask("submit assignment ", true, "by 2025-10-30");
        d.markAsNotDone();
        assertEquals("[D][ ] submit assignment (by: Oct 30 2025)", d.toString());
    }
}
