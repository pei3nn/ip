package finixx.ui;

import finixx.task.DeadlineTask;
import finixx.task.EventTask;
import finixx.task.Task;
import finixx.task.TaskCollection;
import finixx.task.TodoTask;

import java.io.IOException;
import java.util.List;

/**
 * Handles all user interface (UI) interactions for Finixx.
 * This includes printing greetings, task lists, messages, and error notifications.
 */
public class Ui {

    /**
     * Returns the greeting message for the user.
     * @return Greeting string
     */
    public static String printGreeting() {
        String Greeting = "Hi! I'm Finixx \n"
                + "Your personal chatbot companion\n"
                + "What can I do for you today?\n";
        return Greeting;
    }

    /**
     * Returns an error message when saving tasks fails.
     * @param e The IOException encountered during saving
     * @return Error message string
     */
    public static String printErrorSavingTask(IOException e) {
        return "Error saving tasks: " + e.getMessage();
    }

    /**
     * Returns the goodbye message.
     * @return Goodbye string
     */
    public String printGoodbye() {
        return "Goodbye. Hope to see you again!";
    }

    /**
     * Returns a formatted list of all tasks.
     * @param tasks The collection of tasks to display
     * @return String listing all tasks
     */
    public String printList(TaskCollection tasks) {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            listOfTasks.append((i + 1)).append(". ").append(tasks.getTask(i).toString()).append("\n");
        }
        return "Here are the tasks in your list:\n" + listOfTasks;
    }

    /**
     * Returns a message indicating a task has been marked as done.
     * @param tasks The collection of tasks
     * @param taskIndex The index (1-based) of the marked task
     * @return Confirmation message string
     */
    public String printMarked(TaskCollection tasks, int taskIndex) {
        String markedTask = tasks.getTask(taskIndex - 1).toString();
        return "Nice! I've marked this task as done:\n" + markedTask;
    }

    /**
     * Returns a message indicating a task has been marked as not done.
     * @param tasks The collection of tasks
     * @param taskIndex The index (1-based) of the unmarked task
     * @return Confirmation message string
     */
    public String printUnmarked(TaskCollection tasks, int taskIndex) {
        String unmarkedTask = tasks.getTask(taskIndex - 1).toString();
        return "OK, I've marked this task as not done yet:\n" + unmarkedTask;
    }

    /**
     * Returns a message indicating a task has been deleted.
     * @param tasks The collection of tasks
     * @param taskIndex The index (1-based) of the deleted task
     * @return Deletion confirmation message string
     */
    public String printDeleted(TaskCollection tasks, int taskIndex) {
        return "Noted. I've removed this task:\n"
                + tasks.getTask(taskIndex - 1).toString() + "\n"
                + "Now you have " + (tasks.getSize() - 1) + " tasks in the list.";
    }

    /**
     * Returns a message indicating a todo task has been added.
     * @param tasks The collection of tasks
     * @param t The added TodoTask
     * @return Addition confirmation message string
     */
    public String printAddedTodo(TaskCollection tasks, TodoTask t) {
        return "Got it. I've added this task:\n"
                + t.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Returns a message indicating a deadline task has been added.
     * @param tasks The collection of tasks
     * @param d The added DeadlineTask
     * @return Addition confirmation message string
     */
    public String printAddedDeadline(TaskCollection tasks, DeadlineTask d) {
        return "Got it. I've added this task:\n"
                + d.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Returns a message indicating an event task has been added.
     * @param tasks The collection of tasks
     * @param e The added EventTask
     * @return Addition confirmation message string
     */
    public String printAddedEvent(TaskCollection tasks, EventTask e) {
        return "Got it. I've added this task:\n"
                + e.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Returns an error message when file creation fails.
     * @param e The IOException encountered during file creation
     * @return Error message string
     */
    public static String printCreateFileError(IOException e) {
        return "Error creating file: " + e.getMessage();
    }

    /**
     * Returns an error message when loading saved expenses fails.
     * @return Loading error message string
     */
    public String showLoadingError() {
        return "Error loading saved expenses. Creating new list.";
    }

    /**
     * Returns a list of tasks that match the search criteria.
     * @param matchingTasks List of matching tasks
     * @return String listing matching tasks
     */
    public String printFindPossible(List<Task> matchingTasks) {
        StringBuilder matchedTasksList = new StringBuilder();
        for (int i = 0; i < matchingTasks.size(); i++) {
            matchedTasksList.append((i + 1)).append(".").append(matchingTasks.get(i));
        }
        return "Here are the matching tasks in your list:\n"
                + matchedTasksList;
    }

    /**
     * Returns a success message when loading saved expenses succeeds.
     * @return Loading success message string
     */
    public String showLoadingSuccess() {
        return "Loaded saved expenses successfully.";
    }

    /**
     * Returns a message indicating a note has been added to the task
     * @param tasks The collection of tasks
     * @param taskIndex The index (1-based) of the task to add note
     * @return Addition confirmation message string
     */
    public String printAddedNote(TaskCollection tasks, int taskIndex) {
        String taskToAddNote = tasks.getTask(taskIndex - 1).toString();
        return "Got it. I've added your notes to this task:\n" + taskToAddNote.toString();
    }
}
