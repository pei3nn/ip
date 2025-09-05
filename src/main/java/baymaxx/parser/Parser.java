package baymaxx.parser;

import baymaxx.exception.BaymaxxException;
import baymaxx.storage.Storage;
import baymaxx.task.*;
import baymaxx.ui.Ui;

import java.util.List;

public class Parser {

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
        UNKNOWN
    }


    private static Command parseCommand(String input) {
        switch (input.toLowerCase()) {
            case "bye": return Command.BYE;
            case "list": return Command.LIST;
            case "mark": return Command.MARK;
            case "unmark": return Command.UNMARK;
            case "delete": return Command.DELETE;
            case "todo": return Command.TODO;
            case "deadline": return Command.DEADLINE;
            case "event": return Command.EVENT;
            case "find": return Command.FIND;
            default: return Command.UNKNOWN;
        }
    }

    /**
     * Parses input and returns a response string for GUI display.
     */
    public static String parse(String input, Ui ui, TaskCollection tasks, Storage storage) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        Command commandEnum = parseCommand(command);

        String arg = (parts.length > 1) ? parts[1] : "";
        String[] dateParts = arg.split("/", 2);
        String desc = dateParts[0];
        String deadlinePart = (dateParts.length > 1) ? dateParts[1] : "";

        try {
            switch (commandEnum) {
            case BYE:
                return ui.printGoodbye();

            case LIST:
                return ui.printList(tasks);

            case MARK:
                // catch errors
                if (!isInteger(arg)) {
                    throw new BaymaxxException("Oops! " + arg + " is NOT an integer.");
                } else if (Integer.parseInt(arg) < 1 || Integer.parseInt(arg) > tasks.getSize()) {
                    throw new BaymaxxException("Oops! There is no such task number: " + arg);
                }

                // For valid input:
                int taskIndexMark = Integer.parseInt(arg);
                tasks.getTask(taskIndexMark - 1).markAsDone();
                storage.saveTasks(tasks);
                return ui.printMarked(tasks, taskIndexMark);

            case UNMARK:
                // catch errors
                if (!isInteger(arg)) {
                    throw new BaymaxxException("Oops! " + arg + " is NOT an integer.");
                } else if (Integer.parseInt(arg) < 1 || Integer.parseInt(arg) > tasks.getSize()) {
                    throw new BaymaxxException("Oops! There is no such task number: " + arg);
                }

                //For valid input:
                int taskIndexUnmark = Integer.parseInt(arg);
                tasks.getTask(taskIndexUnmark - 1).markAsNotDone();
                storage.saveTasks(tasks);
                return ui.printsUnmarked(tasks, taskIndexUnmark);

            case DELETE:
                // catch errors
                if (!isInteger(arg)) {
                    throw new BaymaxxException("Oops! " + arg + " is NOT an integer.");
                } else if (Integer.parseInt(arg) < 1 || Integer.parseInt(arg) > tasks.getSize()) {
                    throw new BaymaxxException("Oops! There is no such task number: " + arg);
                }

                //For valid input:
                int taskIndexDelete = Integer.parseInt(arg);
                String response = ui.printDeleted(tasks, taskIndexDelete);
                tasks.removeTask(taskIndexDelete - 1);
                storage.saveTasks(tasks);
                return response;

            case TODO:
                // catch errors
                if (arg == "") {
                    throw new BaymaxxException("Oh no! you don't have a description for todo");
                }

                // For valid input:
                TodoTask t = new TodoTask(arg, false);
                tasks.addTask(t);
                storage.saveTasks(tasks);
                return ui.printAddedTodo(tasks, t);

            case DEADLINE:
                // catch errors
                if (arg == "") {
                    throw new BaymaxxException("Oh no! you don't have a description for your task!");
                } else if (!arg.contains("/")) {
                    throw new BaymaxxException("Oh no! you don't have a deadline for your task!");
                }

                // For valid input:
                DeadlineTask d = new DeadlineTask(desc, false, deadlinePart);
                tasks.addTask(d);
                storage.saveTasks(tasks);
                return ui.printAddedDeadline(tasks, d);

            case EVENT:
                // catch errors
                if (arg == "") {
                    throw new BaymaxxException("Oh no! you don't have a description for your task!");
                } else if (!arg.contains("/")) {
                    throw new BaymaxxException("Oh no! you don't have a time for your task!");
                }

                //For valid input:
                EventTask e = new EventTask(desc, false, deadlinePart);
                tasks.addTask(e);
                storage.saveTasks(tasks);
                return ui.printAddedEvent(tasks, e);

            case FIND:
                List<Task> matchingTasks = tasks.findTasks(arg); // arg is the keyword
                return ui.printFindPossible(matchingTasks);

            case UNKNOWN:
            default:
                throw new BaymaxxException("I don't understand that command.");
            }

        } catch (BaymaxxException e) {
            return e.printMessage();
        }
    }

    private static boolean isInteger(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
