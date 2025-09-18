package finixx.parser;

import finixx.storage.Storage;
import finixx.task.DeadlineTask;
import finixx.task.EventTask;
import finixx.task.TaskCollection;
import finixx.task.TodoTask;
import finixx.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Parser class.
 */
public class ParserTest {
    private TaskCollection tasks;
    private Ui ui;
    private Storage storage;

    private void setupEmptyTasks() {
        tasks = new TaskCollection();
    }

    private void setupWithSampleTasks() {
        tasks = new TaskCollection();
        tasks.addTask(new TodoTask("read book", false, ""));
        tasks.addTask(new DeadlineTask("return book", false, "", "2099-12-31"));
        tasks.addTask(new EventTask("project meeting", false, "", "2pm", "4pm"));
    }

    @BeforeEach
    void setupCommon() throws IOException {
        ui = new Ui();
        storage = new Storage("test_tasks.txt");
    }

    // Todo command tests
    @Test
    void parse_todoValid_createsTodo() {
        setupEmptyTasks();
        String input = "todo read book | chapter 1";
        String response = Parser.parse(input, ui, tasks, storage);

        assertEquals(1, tasks.getSize());
        assertTrue(tasks.getTask(0) instanceof TodoTask);
        assertEquals("read book", tasks.getTask(0).getDescription());
        assertEquals("chapter 1", ((TodoTask) tasks.getTask(0)).getNote());
        assertTrue(response.contains("read book"));
    }

    @Test
    void parse_todoEmptyDescription_throwsException() {
        setupEmptyTasks();
        String input = "todo   ";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("Oh no! you must have a description"));
        assertEquals(0, tasks.getSize());
    }

    // Deadline command tests
    @Test
    void parse_deadlineValid_createsDeadline() {
        setupEmptyTasks();
        LocalDate futureDate = LocalDate.now().plusDays(1);
        String input = "deadline return book /by " + futureDate;
        String response = Parser.parse(input, ui, tasks, storage);

        assertEquals(1, tasks.getSize());
        assertTrue(tasks.getTask(0) instanceof DeadlineTask);
        assertEquals("return book", tasks.getTask(0).getDescription());
        assertEquals(futureDate.toString(), ((DeadlineTask) tasks.getTask(0)).getDeadline());
        assertTrue(response.contains("return book"));
    }

    @Test
    void parse_deadlineMissingBy_throwsException() {
        setupEmptyTasks();
        String input = "deadline return book";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("Invalid deadline format"));
        assertEquals(0, tasks.getSize());
    }

    @Test
    void parse_deadlinePastDate_throwsException() {
        setupEmptyTasks();
        String input = "deadline return book /by 2000-01-01";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("Deadline cannot be before today"));
        assertEquals(0, tasks.getSize());
    }

    @Test
    void parse_deadlineEmptyDescription_throwsException() {
        setupEmptyTasks();
        String input1 = "deadline   /by 2025-10-30";
        String input2 = "deadline   ";
        String response1 = Parser.parse(input1, ui, tasks, storage);
        String response2 = Parser.parse(input2, ui, tasks, storage);

        assertTrue(response1.contains("Oh no! you must have a description for your task!"));
        assertTrue(response2.contains("Oh no! you must have a description for your task!"));
        assertEquals(0, tasks.getSize());
    }

    @Test
    void parse_deadlineInvalidDateFormat_throwsException() {
        setupEmptyTasks();
        String input = "deadline return book /by sunday";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("Deadline must be in yyyy-MM-dd format!"));
        assertEquals(0, tasks.getSize());
    }

    // Event command tests
    @Test
    void parse_eventValid_createsEvent() {
        setupEmptyTasks();
        String input = "event project meeting /from 2pm /to 4pm";
        String response = Parser.parse(input, ui, tasks, storage);

        assertEquals(1, tasks.getSize());
        assertTrue(tasks.getTask(0) instanceof EventTask);
        EventTask event = (EventTask) tasks.getTask(0);
        assertEquals("project meeting", event.getDescription());
        assertEquals("2pm", event.getStart());
        assertEquals("4pm", event.getEnd());
        assertTrue(response.contains("project meeting"));
    }

    @Test
    void parse_eventMissingStartOrEndTime_throwsException() {
        setupEmptyTasks();
        String input1 = "event project meeting /from 2pm /to";
        String input2 = "event project meeting /from /to 4pm";

        String response1 = Parser.parse(input1, ui, tasks, storage);
        String response2 = Parser.parse(input2, ui, tasks, storage);

        assertTrue(response1.contains("End time cannot be empty!"));
        assertTrue(response2.contains("Start time cannot be empty!"));
        assertEquals(0, tasks.getSize());
    }

    @Test
    void parse_eventMissingFromOrTo_throwsException() {
        setupEmptyTasks();
        String input1 = "event project meeting /to 4pm";
        String input2 = "event project meeting /from 2pm";

        String response1 = Parser.parse(input1, ui, tasks, storage);
        String response2 = Parser.parse(input2, ui, tasks, storage);

        assertTrue(response1.contains("Invalid event format"));
        assertTrue(response2.contains("Invalid event format"));
        assertEquals(0, tasks.getSize());
    }

    @Test
    void parse_eventEmptyDescription_throwsException() {
        setupEmptyTasks();
        String input1 = "event   /from 2pm /to 4pm";
        String input2 = "event   ";

        String response1 = Parser.parse(input1, ui, tasks, storage);
        String response2 = Parser.parse(input2, ui, tasks, storage);

        assertTrue(response1.contains("Oh no! you must have a description for your task!"));
        assertTrue(response2.contains("Oh no! you must have a description for your task!"));
        assertEquals(0, tasks.getSize());
    }

    // Mark command tests
    @Test
    void parse_markValid_marksTask() {
        setupWithSampleTasks();
        String input = "mark 1";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(tasks.getTask(0).isDone());
        assertTrue(response.contains("[X] read book"));
    }

    @Test
    void parse_markInvalidTaskNumber_throwsException() {
        setupWithSampleTasks();
        String input = "mark 10";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("no such task number"));
        assertFalse(tasks.getTask(0).isDone());
    }

    @Test
    void parse_markNonInteger_throwsException() {
        setupWithSampleTasks();
        String input = "mark abc";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("is NOT an integer"));
        assertFalse(tasks.getTask(0).isDone());
    }

    // Unmark command tests
    @Test
    void parse_unmarkValid_unmarksTask() {
        setupWithSampleTasks();
        tasks.getTask(0).markAsDone(); // mark first
        String input = "unmark 1";
        String response = Parser.parse(input, ui, tasks, storage);

        assertFalse(tasks.getTask(0).isDone());
        assertTrue(response.contains("[ ] read book"));
    }

    @Test
    void parse_unmarkInvalidTaskNumber_throwsException() {
        setupWithSampleTasks();
        tasks.getTask(0).markAsDone(); // mark first
        String input = "unmark 10";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("no such task number"));
        assertTrue(tasks.getTask(0).isDone());
    }

    @Test
    void parse_unmarkNonInteger_throwsException() {
        setupWithSampleTasks();
        tasks.getTask(0).markAsDone(); // mark first
        String input = "unmark abc";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("is NOT an integer"));
        assertTrue(tasks.getTask(0).isDone());
    }

    // Delete command tests
    @Test
    void parse_deleteValid_deletesTask() {
        setupWithSampleTasks();
        String input = "delete 2";
        String response = Parser.parse(input, ui, tasks, storage);

        assertEquals(2, tasks.getSize()); // one removed
        assertFalse(tasks.getTask(1).getDescription().equals("return book"));
        assertTrue(response.contains("return book"));
    }

    @Test
    void parse_deleteInvalidTaskNumber_throwsException() {
        setupWithSampleTasks();
        String input = "delete 5";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("no such task number"));
        assertEquals(3, tasks.getSize());
    }

    @Test
    void parse_deleteNonInteger_throwsException() {
        setupWithSampleTasks();
        String input = "delete abc";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("is NOT an integer"));
        assertEquals(3, tasks.getSize());
    }

    // Find command tests
    @Test
    void parse_findValid_returnsMatchingTasks() {
        setupWithSampleTasks();
        String input = "find book";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("read book"));
        assertTrue(response.contains("return book"));
    }

    @Test
    void parse_findNoMatches_returnsEmptyMessage() {
        setupWithSampleTasks();
        String input = "find homework";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("Here are the matching tasks in your list"));
        assertFalse(response.contains("read book"));
    }

    // Note command tests
    @Test
    void parse_noteValid_addsNote() {
        setupWithSampleTasks();
        String input = "note 1 Chapter 1 complete";
        String response = Parser.parse(input, ui, tasks, storage);

        assertEquals("Chapter 1 complete", tasks.getTask(0).getNote());
        assertTrue(response.contains("updated your notes"));
    }

    @Test
    void parse_noteEmptyNotes_addsEmptyNote() {
        setupWithSampleTasks();
        String input = "note 2   "; // no note text
        String response = Parser.parse(input, ui, tasks, storage);

        assertEquals("", tasks.getTask(1).getNote());
        assertTrue(response.contains("updated your notes"));
    }

    @Test
    void parse_noteInvalidTaskNumber_throwsException() {
        setupWithSampleTasks();
        String input = "note 5 Some note";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("no such task number"));
        assertEquals("", tasks.getTask(0).getNote());
        assertEquals("", tasks.getTask(1).getNote());
    }

    @Test
    void parse_noteNonIntegerTaskNumber_throwsException() {
        setupWithSampleTasks();
        String input = "note abc Some note";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("is NOT an integer"));
        assertEquals("", tasks.getTask(0).getNote());
        assertEquals("", tasks.getTask(1).getNote());
    }

    @Test
    void parse_noteExtraSpaces_trimsCorrectly() {
        setupWithSampleTasks();
        String input = "   note   1   Finished reading chapter 2   ";
        String response = Parser.parse(input, ui, tasks, storage);

        assertEquals("Finished reading chapter 2", tasks.getTask(0).getNote());
        assertTrue(response.contains("updated your notes"));
    }

    // Unknown command test
    @Test
    void parse_unknownCommand_throwsException() {
        setupEmptyTasks();
        String input = "hi";
        String response = Parser.parse(input, ui, tasks, storage);

        assertTrue(response.contains("I don't understand that command"));
        assertEquals(0, tasks.getSize());
    }
}
