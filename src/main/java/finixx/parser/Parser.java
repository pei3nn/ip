package finixx.parser;

import finixx.exception.FinixxException;
import finixx.storage.Storage;
import finixx.task.DeadlineTask;
import finixx.task.EventTask;
import finixx.task.Task;
import finixx.task.TaskCollection;
import finixx.task.TodoTask;
import finixx.ui.Ui;

import java.util.List;

/**
 * Parses user input and executes corresponding commands.
 */
public class Parser {

    /**
     * Enum representing the various commands that can be parsed.
     */
    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        NOTE,
        UNKNOWN
    }

    /**
     * Parses the command string and returns the corresponding Command enum.
     * 
     * @param input The command string from user input
     * @return The matching Command enum value
     */
    private static Command parseCommand(String input) {
        return switch (input.toLowerCase()) {
            case "bye" -> Command.BYE;
            case "list" -> Command.LIST;
            case "mark" -> Command.MARK;
            case "unmark" -> Command.UNMARK;
            case "delete" -> Command.DELETE;
            case "todo" -> Command.TODO;
            case "deadline" -> Command.DEADLINE;
            case "event" -> Command.EVENT;
            case "find" -> Command.FIND;
            case "note" -> Command.NOTE;
            default -> Command.UNKNOWN;
        };
    }

    /**
     * Parses input and returns a response string for GUI display.
     * 
     * @param input   The full user input string
     * @param ui      The UI instance for displaying messages
     * @param tasks   The collection of tasks to operate on
     * @param storage The storage handler for saving tasks
     * @return The response string to display in the GUI
     */
    public static String parse(String input, Ui ui, TaskCollection tasks, Storage storage) {
        String[] parts = input.split(" ", 2);
        assert parts.length >= 1 : "There should be at least one command in the input!";

        String command = parts[0];
        Command commandEnum = parseCommand(command);
        String arg = (parts.length > 1) ? parts[1] : "";

        try {
            switch (commandEnum) {
            case BYE:
                return ui.printGoodbye();

            case LIST:
                return ui.printList(tasks);

            case MARK:
                validateTaskIndex(arg, tasks);

                int taskIndexMark = Integer.parseInt(arg);
                tasks.getTask(taskIndexMark - 1).markAsDone();
                storage.saveTasks(tasks);
                return ui.printMarked(tasks, taskIndexMark);

            case UNMARK:
                validateTaskIndex(arg, tasks);

                int taskIndexUnmark = Integer.parseInt(arg);
                tasks.getTask(taskIndexUnmark - 1).markAsNotDone();
                storage.saveTasks(tasks);
                return ui.printUnmarked(tasks, taskIndexUnmark);

            case DELETE:
                validateTaskIndex(arg, tasks);

                int taskIndexDelete = Integer.parseInt(arg);
                String response = ui.printDeleted(tasks, taskIndexDelete);
                tasks.removeTask(taskIndexDelete - 1);
                storage.saveTasks(tasks);
                return response;

            case TODO:
                TodoTask t = createTodoTask(arg);
                tasks.addTask(t);
                storage.saveTasks(tasks);
                return ui.printAddedTodo(tasks, t);

            case DEADLINE:
                DeadlineTask d = createDeadlineTask(arg);
                tasks.addTask(d);
                storage.saveTasks(tasks);
                return ui.printAddedDeadline(tasks, d);

            case EVENT:
                EventTask e = createEventTask(arg);
                tasks.addTask(e);
                storage.saveTasks(tasks);
                return ui.printAddedEvent(tasks, e);

            case FIND:
                List<Task> matchingTasks = tasks.findTasks(arg);
                return ui.printFindPossible(matchingTasks);

            case NOTE:
                String[] details = arg.split(" ", 2);
                String taskIndex = details[0];
                validateTaskIndex(taskIndex, tasks);

                int taskIndexNote = Integer.parseInt(taskIndex);
                String notes = (details.length > 1) ? details[1] : "";
                tasks.getTask(taskIndexNote - 1).addNote(notes);
                storage.saveTasks(tasks);
                return ui.printAddedNote(tasks, taskIndexNote);

            case UNKNOWN:
            default:
                throw new FinixxException("Sorry... I don't understand that command.");
            }

        } catch (FinixxException e) {
            return e.printMessage();
        }
    }

    private static String extractTaskIndex(String arg) {
        String[] details = arg.split(" ", 2);
        return details[0];
    }

    private static void addTaskNote(TaskCollection tasks, String taskIndex, String notes) {

    }

    private static boolean isInteger(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void validateTaskIndex(String arg, TaskCollection tasks) throws FinixxException {
        assert !arg.equals("") : "task number to be marked should not be empty!";
        if (!isInteger(arg)) {
            throw new FinixxException("Oops! " + arg + " is NOT an integer.");
        } else if (Integer.parseInt(arg) < 1 || Integer.parseInt(arg) > tasks.getSize()) {
            throw new FinixxException("Oops! There is no such task number: " + arg);
        }
    }

    private static TodoTask createTodoTask(String arg) throws FinixxException {
        String[] argParts = arg.split(" \\| ", 2);
        String description = argParts[0];
        String notes = (argParts.length > 1) ? argParts[1] : "";

        if (description.isEmpty()) {
            throw new FinixxException("Oh no! you don't have a description for todo");
        }

        return new TodoTask(description, false, notes);
    }

    private static DeadlineTask createDeadlineTask(String arg)
            throws FinixxException {

        String[] argParts = arg.split(" \\| ", 2);
        String details = argParts[0];
        String notes = (argParts.length > 1) ? argParts[1] : "";

        if (details.isEmpty()) {
            throw new FinixxException("Oh no! you don't have a description for your task!");
        }

        String[] detailParts = details.split("/", 2);
        String description = detailParts[0];
        String deadline = (detailParts.length > 1) ? detailParts[1] : "";

        if (deadline.isEmpty()) {
            throw new FinixxException("Oh no! you don't have a deadline for your task!");
        }

        return new DeadlineTask(description, false, notes, deadline);
    }

    private static EventTask createEventTask(String arg) throws FinixxException {
        String[] argParts = arg.split(" \\| ", 2);
        String details = argParts[0];
        String notes = (argParts.length > 1) ? argParts[1] : "";

        if (details.isEmpty()) {
            throw new FinixxException("Oh no! you don't have a description for your task!");
        } else if (!details.contains("/")) {
            throw new FinixxException("Oh no! you don't have a time for your task!");
        }

        String[] detailParts = details.split("/", 2);
        String description = detailParts[0];
        String timeOfTask = (detailParts.length > 1) ? detailParts[1] : "";

        return new EventTask(description, false, notes, timeOfTask);
    }
}
