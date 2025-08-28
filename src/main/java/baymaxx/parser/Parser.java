package baymaxx.parser;

import baymaxx.exception.BaymaxxException;
import baymaxx.storage.Storage;
import baymaxx.task.DeadlineTask;
import baymaxx.task.EventTask;
import baymaxx.task.TaskCollection;
import baymaxx.task.TodoTask;
import baymaxx.ui.Ui;

public class Parser {

    public static boolean isExit;

    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
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
            default: return Command.UNKNOWN;
        }
    }

    public static void parse(String input, Ui ui, TaskCollection tasks, Storage storage) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        Command commandEnum = parseCommand(command);

        String arg = (parts.length > 1) ? parts[1] : "";
        String[] dateParts = arg.split("/", 2);
        String desc = dateParts[0];
        String deadlinePart = (dateParts.length > 1) ? dateParts[1] : "";

        switch (commandEnum) {
            case BYE:
                ui.printGoodbye();
                isExit = true;
                return;

            case LIST:
                ui.printList(tasks);
                break;

            case MARK:
                // catch errors
                try {
                    if (!isInteger(arg)) {
                        throw new BaymaxxException("(>.<) Oops! " + arg + " is NOT an integer.");
                    } else if (Integer.parseInt(arg) < 1 || Integer.parseInt(arg) > tasks.getSize()) {
                        throw new BaymaxxException("(>.<) Oops! There is no such baymaxx.task number: " + arg);
                    }

                    // For valid input:
                    int taskIndex = Integer.parseInt(arg) - 1;
                    tasks.getTask(taskIndex).markAsDone();
                    storage.saveTasks(tasks);
                    ui.printMarked(tasks, taskIndex);

                } catch (BaymaxxException e) {
                    e.printMessage();
                }
                break;

            case UNMARK:
                // catch errors
                try {
                    if (!isInteger(arg)) {
                        throw new BaymaxxException("(>.<) Oops! " + arg + " is NOT an integer.");
                    } else if (Integer.parseInt(arg) < 1 || Integer.parseInt(arg) > tasks.getSize()) {
                        throw new BaymaxxException("(>.<) Oops! There is no such baymaxx.task number: " + arg);
                    }

                    //For valid input:
                    int taskIndex = Integer.parseInt(arg) - 1;
                    tasks.getTask(taskIndex).markAsNotDone();
                    storage.saveTasks(tasks);
                    ui.printsUnmarked(tasks, taskIndex);

                } catch (BaymaxxException e) {
                    e.printMessage();
                }
                break;

            case DELETE:
                // catch errors
                try {
                    if (!isInteger(arg)) {
                        throw new BaymaxxException("(>.<) Oops! " + arg + " is NOT an integer.");
                    } else if (Integer.parseInt(arg) < 1 || Integer.parseInt(arg) > tasks.getSize()) {
                        throw new BaymaxxException("(>.<) Oops! There is no such baymaxx.task number: " + arg);
                    }

                    //For valid input:
                    int taskIndex = Integer.parseInt(arg) - 1;
                    ui.printDeleted(tasks, taskIndex);
                    tasks.removeTask(taskIndex);
                    storage.saveTasks(tasks);

                } catch (BaymaxxException e) {
                    e.printMessage();
                }
                break;

            case TODO:
                // catch errors
                try {
                    if (arg == "") {
                        throw new BaymaxxException("(>.<) Oh no! you don't have a description for todo");
                    }

                    // For valid input:
                    TodoTask t = new TodoTask(arg, false);
                    tasks.addTask(t);
                    storage.saveTasks(tasks);
                    ui.printAddedTodo(tasks, t);

                } catch (BaymaxxException e) {
                    e.printMessage();
                }
                break;

            case DEADLINE:
                // catch errors
                try {
                    if (arg == "") {
                        throw new BaymaxxException("(>.<) Oh no! you don't have a description for your baymaxx.task!");
                    } else if (!arg.contains("/")) {
                        throw new BaymaxxException("(>.<) Oh no! you don't have a deadline for your baymaxx.task!");
                    }

                    // For valid input:
                    DeadlineTask d = new DeadlineTask(desc, false, deadlinePart);
                    tasks.addTask(d);
                    storage.saveTasks(tasks);
                    ui.printAddedDeadline(tasks, d);

                } catch (BaymaxxException e) {
                    e.printMessage();
                }
                break;

            case EVENT:
                // catch errors
                try {
                    if (arg == "") {
                        throw new BaymaxxException("(>.<) Oh no! you don't have a description for your baymaxx.task!");
                    } else if (!arg.contains("/")) {
                        throw new BaymaxxException("(>.<) Oh no! you don't have a time for your baymaxx.task!");
                    }

                    //For valid input:
                    EventTask e = new EventTask(desc, false, deadlinePart);
                    tasks.addTask(e);
                    storage.saveTasks(tasks);
                    ui.printAddedEvent(tasks, e);

                } catch (BaymaxxException e) {
                    e.printMessage();
                }
                break;

            case UNKNOWN:
                try {
                    throw new BaymaxxException("(O.O) Sorry! I don't know what you mean.");
                } catch (BaymaxxException e) {
                    e.printMessage();
                }
                break;
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
