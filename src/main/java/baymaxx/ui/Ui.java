package baymaxx.ui;

import baymaxx.task.DeadlineTask;
import baymaxx.task.EventTask;
import baymaxx.task.TaskCollection;
import baymaxx.task.TodoTask;

import java.io.IOException;

public class Ui {

    /**
     * Prints greetings
     */
    public static void printGreeting() {
        String Greeting = "Hi! I'm Baymaxx (●─●)\n"
                + "Your personal chatbot assistant\n"
                + "What can I do for you?\n";
        System.out.println(Greeting);
    }

    public static void printErrorSavingTask(IOException e) {
        System.out.println("Error saving tasks: " + e.getMessage());
    }

    /**
     * Prints goodbye message
     */
    public void printGoodbye() {
        String Exit = "Goodbye. Hope to see you again soon! (●─●)\n";
        System.out.println("────────────────────────────────────────────────\n"
                + "👋" + Exit);

    }

    /**
     * Prints prompt line
     */
    public void printPromptLine() {
        System.out.print("Input here (^-^): ");
    }

    /**
     * Prints list of tasks
     */
    public void printList(TaskCollection tasks) {
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.getTask(i).toString());
        }
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    /**
     * Prints marks task
     */
    public void printMarked(TaskCollection tasks, int taskIndex) {
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.println("    Nice! I've marked this baymaxx.task as done:");
        System.out.println("       " + tasks.getTask(taskIndex).toString());
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    /**
     * Prints unmarked task
     */
    public void printsUnmarked(TaskCollection tasks, int taskIndex) {
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.println("    OK, I've marked this baymaxx.task as not done yet:");
        System.out.println("       " + tasks.getTask(taskIndex).toString());
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    /**
     * Prints deleted task
     */
    public void printDeleted(TaskCollection tasks, int taskIndex) {
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.println("    Noted. I've removed this baymaxx.task:");
        System.out.println("       " + tasks.getTask(taskIndex).toString());
        System.out.println("    Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    /**
     * Prints added task for todo
     */
    public void printAddedTodo(TaskCollection tasks, TodoTask t) {
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.println("    Got it. I've added this baymaxx.task:");
        System.out.println("       " + t.toString());
        System.out.println("    Now you have " + tasks.getSize() + " tasks in the list.");
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    /**
     * Prints added task for deadline
     */
    public void printAddedDeadline(TaskCollection tasks, DeadlineTask d) {
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.println("    Got it. I've added this baymaxx.task:");
        System.out.println("       " + d.toString());
        System.out.println("    Now you have " + tasks.getSize() + " tasks in the list.");
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    /**
     * Prints added task for event
     */
    public void printAddedEvent(TaskCollection tasks, EventTask e) {
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.println("    Got it. I've added this baymaxx.task:");
        System.out.println("       " + e.toString());
        System.out.println("    Now you have " + tasks.getSize() + " tasks in the list.");
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("═");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    /**
     * Prints error when creating file
     */
    public static void printCreateFileError(IOException e) {
        System.out.println("Error creating file: " + e.getMessage());
    }

    /**
     * Prints loading error message
     */
    public void showLoadingError() {
        System.out.println("Error loading saved expenses. Creating new list.");
    }

    /**
     * Prints loading success message
     */
    public void showLoadingSuccess() {
        System.out.println("Loaded saved expenses successfully.");
    }
}
