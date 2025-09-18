package finixx.task;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskCollectionTest {
    @Test
    public void testAddTask() {
        TaskCollection tasks = new TaskCollection();
        TodoTask t1 = new TodoTask("join sports club", true, "");
        tasks.addTask(t1);

        // Convert tasks to string for comparison
        List<String> actual = tasks.getAllTasks().stream()
                .map(Task::toString)
                .toList();
        List<String> expected = List.of("[T][X] join sports club");

        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteTask() {
        TaskCollection tasks = new TaskCollection();
        TodoTask t1 = new TodoTask("join sports club", true, "");
        TodoTask t2 = new TodoTask("read book", false, "Harry Potter");
        tasks.addTask(t1);
        tasks.addTask(t2);

        // Remove the first task
        tasks.removeTask(0); // assuming removeTask(int index) exists

        // Convert remaining tasks to string
        List<String> actual = tasks.getAllTasks().stream()
                .map(Task::toString)
                .toList();
        List<String> expected = List.of("[T][ ] read book {Harry Potter}");

        assertEquals(expected, actual);
    }

    @Test
    public void testAddDifferentTaskTypes() {
        TaskCollection tasks = new TaskCollection();
        tasks.addTask(new TodoTask("exercise", false, ""));
        tasks.addTask(new DeadlineTask("submit report", false, "", "2025-12-01"));
        tasks.addTask(new EventTask("meeting", false, "office", "2pm", "4pm"));

        List<String> actual = tasks.getAllTasks().stream()
                .map(Task::toString)
                .toList();

        List<String> expected = List.of(
                "[T][ ] exercise",
                "[D][ ] submit report (by: Dec 1 2025)",
                "[E][ ] meeting (from: 2pm to: 4pm) {office}"
        );

        assertEquals(expected, actual);
        assertEquals(3, tasks.getSize());
    }
}
