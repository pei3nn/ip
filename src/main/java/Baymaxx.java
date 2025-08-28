import storage.Storage;
import task.DeadlineTask;
import task.EventTask;
import task.TaskCollection;
import task.TodoTask;

import java.util.Scanner;

public class Baymaxx {
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

    public static void main(String[] args) throws BaymaxxException {
        Scanner sc = new Scanner(System.in);

        // Load data when chatbot starts
        Storage storage = new Storage("data/tasks.txt");
        TaskCollection tasks = storage.loadTasks();

        String Greeting = "Hi! I'm Baymaxx (●─●)\n"
                + "Your personal chatbot assistant\n"
                + "What can I do for you?\n";
        String Exit = "Goodbye. Hope to see you again soon! (●─●)\n";
        System.out.println(Greeting);

        while (true) {
            System.out.print("Input here (^-^): ");
            String input = sc.nextLine().trim();

            String[] parts = input.split(" ", 2);
            String command = parts[0];
            Command commandEnum = parseCommand(command);

            String arg = (parts.length > 1) ? parts[1] : "";
            String[] dateParts = arg.split("/", 2);
            String desc = dateParts[0];
            String deadlinePart = (dateParts.length > 1) ? dateParts[1] : "";

            switch (commandEnum) {
                case BYE:
                    System.out.println("────────────────────────────────────────────────\n"
                            + "👋" + Exit);
                    sc.close();
                    return;

                case LIST:
                    // Formatting output
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
                    break;

                case MARK:
                    // catch errors
                    try {
                        if (!isInteger(arg)) {
                            throw new BaymaxxException("(>.<) Oops! " + arg + " is NOT an integer.");
                        } else if (Integer.parseInt(arg) < 1 || Integer.parseInt(arg) > tasks.getSize()) {
                            throw new BaymaxxException("(>.<) Oops! There is no such task number: " + arg);
                        }

                        // For valid input:
                        int taskIndex = Integer.parseInt(arg) - 1;
                        tasks.getTask(taskIndex).markAsDone();
                        storage.saveTasks(tasks);

                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("       " + tasks.getTask(taskIndex).toString());
                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.print("\n");

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
                            throw new BaymaxxException("(>.<) Oops! There is no such task number: " + arg);
                        }

                        //For valid input:
                        int taskIndex = Integer.parseInt(arg) - 1;
                        tasks.getTask(taskIndex).markAsNotDone();
                        storage.saveTasks(tasks);


                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.println("    OK, I've marked this task as not done yet:");
                        System.out.println("       " + tasks.getTask(taskIndex).toString());
                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.print("\n");

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
                            throw new BaymaxxException("(>.<) Oops! There is no such task number: " + arg);
                        }

                        //For valid input:
                        int taskIndex = Integer.parseInt(arg) - 1;

                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.println("    Noted. I've removed this task:");
                        System.out.println("       " + tasks.getTask(taskIndex).toString());
                        tasks.removeTask(taskIndex);
                        storage.saveTasks(tasks);

                        System.out.println("    Now you have " + tasks.getSize() + " tasks in the list.");
                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.print("\n");



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


                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("       " + t.toString());
                        System.out.println("    Now you have " + tasks.getSize() + " tasks in the list.");
                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.print("\n");

                    } catch (BaymaxxException e) {
                        e.printMessage();
                    }
                    break;

                case DEADLINE:
                    // catch errors
                    try {
                        if (arg == "") {
                            throw new BaymaxxException("(>.<) Oh no! you don't have a description for your task!");
                        } else if (!arg.contains("/")) {
                            throw new BaymaxxException("(>.<) Oh no! you don't have a deadline for your task!");
                        }

                        // For valid input:
                        DeadlineTask d = new DeadlineTask(desc, false, deadlinePart);
                        tasks.addTask(d);
                        storage.saveTasks(tasks);


                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("       " + d.toString());
                        System.out.println("    Now you have " + tasks.getSize() + " tasks in the list.");
                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.print("\n");

                    } catch (BaymaxxException e) {
                        e.printMessage();
                    }
                    break;

                case EVENT:
                    // catch errors
                    try {
                        if (arg == "") {
                            throw new BaymaxxException("(>.<) Oh no! you don't have a description for your task!");
                        } else if (!arg.contains("/")) {
                            throw new BaymaxxException("(>.<) Oh no! you don't have a time for your task!");
                        }

                        //For valid input:
                        EventTask e = new EventTask(desc, false, deadlinePart);
                        tasks.addTask(e);
                        storage.saveTasks(tasks);


                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("       " + e.toString());
                        System.out.println("    Now you have " + tasks.getSize() + " tasks in the list.");
                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.print("\n");

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
    }

    public static boolean isInteger(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
