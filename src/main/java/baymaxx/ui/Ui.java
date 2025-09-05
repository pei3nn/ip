package baymaxx.ui;

import baymaxx.task.*;

import java.io.IOException;
import java.util.List;

/**
 * Handles all user interface (UI) interactions for baymaxx.Baymaxx.
 * This includes printing greetings, task lists, messages, and error notifications.
 */
public class Ui {

    /**
     * Returns greetings
     */
    public static String printGreeting() {
        String Greeting = "Hi! I'm Baymaxx (●─●)\n"
                + "Your personal chatbot companion\n"
                + "What can I do for you today?\n";
        return Greeting;
    }

    /**
     * Returns error message when saving tasks
     */
    public static String printErrorSavingTask(IOException e) {
        return "Error saving tasks: " + e.getMessage();
    }

    /**
     * Returns goodbye message
     */
    public String printGoodbye() {
        return "Goodbye. BALALALALA!";
    }

    /**
     * Returns list of tasks
     */
    public String printList(TaskCollection tasks) {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            listOfTasks.append((i + 1)).append(". ").append(tasks.getTask(i).toString()).append("\n");
        }
        return "Here are the tasks in your list:\n" + listOfTasks;
    }

    /**
     * Returns marks task
     */
    public String printMarked(TaskCollection tasks, int taskIndex) {
        String markedTask = tasks.getTask(taskIndex - 1).toString();
        return "Nice! I've marked this task as done:\n" + markedTask;
    }

    /**
     * Returns unmarked task
     */
    public String printsUnmarked(TaskCollection tasks, int taskIndex) {
        String unmarkedTask = tasks.getTask(taskIndex - 1).toString();
        return "OK, I've marked this task as not done yet:\n" + unmarkedTask;
    }

    /**
     * Returns deleted task
     */
    public String printDeleted(TaskCollection tasks, int taskIndex) {
        return "Noted. I've removed this task:\n"
                + tasks.getTask(taskIndex - 1).toString() + "\n"
                + "Now you have " + (tasks.getSize() - 1) + " tasks in the list.";
    }

    /**
     * Returns added task for todo
     */
    public String printAddedTodo(TaskCollection tasks, TodoTask t) {
        return "Got it. I've added this task:\n"
                + t.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Returns added task for deadline
     */
    public String printAddedDeadline(TaskCollection tasks, DeadlineTask d) {
        return "Got it. I've added this task:\n"
                + d.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Returns added task for event
     */
    public String printAddedEvent(TaskCollection tasks, EventTask e) {
        return "Got it. I've added this task:\n"
                + e.toString() + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Returns error when creating file
     */
    public static String printCreateFileError(IOException e) {
        return "Error creating file: " + e.getMessage();
    }

    /**
     * Returns loading error message
     */
    public String showLoadingError() {
        return "Error loading saved expenses. Creating new list.";
    }
    
    /**
     * Returns find possible tasks
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
     * Returns loading success message
     */
    public String showLoadingSuccess() {
        return "Loaded saved expenses successfully.";
    }
}
