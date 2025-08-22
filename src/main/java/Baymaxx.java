import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Baymaxx {
    public static void main(String[] args) throws BaymaxxException {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

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
            String arg = (parts.length > 1) ? parts[1] : "";
            String[] dateParts = arg.split("/", 2);
            String desc = dateParts[0];
            String deadlinePart = (dateParts.length > 1) ? dateParts[1] : "";

            switch (command) {
                case "bye":
                    System.out.println("────────────────────────────────────────────────\n"
                            + "👋" + Exit);
                    sc.close();
                    return;

                case "list":
                    // Formatting output
                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print("\n");
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("    " + (i + 1) + ". " + tasks.get(i).toString());
                    }
                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print("\n");
                    System.out.print("\n");
                    break;

                case "mark":
                    // catch errors
                    try {
                        if (!isInteger(arg)) {
                            throw new BaymaxxException("(>.<) Oops! " + arg + " is NOT an integer.");
                        } else if (Integer.parseInt(arg) < 1 || Integer.parseInt(arg) > tasks.size()) {
                            throw new BaymaxxException("(>.<) Oops! There is no such task number: " + arg);
                        }

                        // For valid input:
                        int taskIndex = Integer.parseInt(arg) - 1;
                        tasks.get(taskIndex).markAsDone();

                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("       " + tasks.get(taskIndex).toString());
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

                case "unmark":
                    // catch errors
                    try {
                        if (!isInteger(arg)) {
                            throw new BaymaxxException("(>.<) Oops! " + arg + " is NOT an integer.");
                        } else if (Integer.parseInt(arg) < 1 || Integer.parseInt(arg) > tasks.size()) {
                            throw new BaymaxxException("(>.<) Oops! There is no such task number: " + arg);
                        }

                        //For valid input:
                        int taskIndex = Integer.parseInt(arg) - 1;
                        tasks.get(taskIndex).markAsNotDone();

                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.println("    OK, I've marked this task as not done yet:");
                        System.out.println("       " + tasks.get(taskIndex).toString());
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

                case "todo":
                    // catch errors
                    try {
                        if (arg == "") {
                            throw new BaymaxxException("(>.<) Oh no! you don't have a description for todo");
                        }

                        // For valid input:
                        TodoTask t = new TodoTask(arg);
                        tasks.add(t);

                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("       " + t.toString());
                        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
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

                case "deadline":
                    // catch errors
                    try {
                        if (arg == "") {
                            throw new BaymaxxException("(>.<) Oh no! you don't have a description for your task!");
                        } else if (!arg.contains("/")) {
                            throw new BaymaxxException("(>.<) Oh no! you don't have a deadline for your task!");
                        }

                        // For valid input:
                        DeadlineTask d = new DeadlineTask(desc, deadlinePart);
                        tasks.add(d);

                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("       " + d.toString());
                        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
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

                case "event":
                    // catch errors
                    try {
                        if (arg == "") {
                            throw new BaymaxxException("(>.<) Oh no! you don't have a description for your task!");
                        } else if (!arg.contains("/")) {
                            throw new BaymaxxException("(>.<) Oh no! you don't have a time for your task!");
                        }

                        //For valid input:
                        EventTask e = new EventTask(desc, deadlinePart);
                        tasks.add(e);

                        System.out.print("  ");
                        for (int i = 0; i < 50; i++) {
                            System.out.print("═");
                        }
                        System.out.print("\n");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("       " + e.toString());
                        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
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

                default:
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
