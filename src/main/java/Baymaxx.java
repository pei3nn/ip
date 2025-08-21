import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Baymaxx {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        String Greeting = "Hi! I'm Baymaxx (●─●) \n"
                + "Your personal chatbot assistant \n"
                + "What can I do for you? \n";
        String Exit = "Goodbye. Hope to see you again soon! (●─●) \n";
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
                    System.out.print(" \n");
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("    " + (i + 1) + ". " + tasks.get(i).toString());
                    }
                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print(" \n");
                    System.out.print(" \n");
                    break;

                case "mark":
                    int taskIndex = Integer.parseInt(arg) - 1;
                    tasks.get(taskIndex).markAsDone();

                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print(" \n");
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("       " + tasks.get(taskIndex).toString());
                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print(" \n");
                    System.out.print(" \n");
                    break;

                case "unmark":
                    taskIndex = Integer.parseInt(arg) - 1;
                    tasks.get(taskIndex).markAsNotDone();

                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print(" \n");
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("       " + tasks.get(taskIndex).toString());
                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print(" \n");
                    System.out.print(" \n");
                    break;

                case "todo":
                    TodoTask t = new TodoTask(arg);
                    tasks.add(t);

                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print(" \n");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("       " + t.toString());
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print(" \n");
                    System.out.print(" \n");
                    break;

                case "deadline":
                    DeadlineTask d = new DeadlineTask(desc, deadlinePart);
                    tasks.add(d);

                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print(" \n");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("       " + d.toString());
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print(" \n");
                    System.out.print(" \n");
                    break;

                case "event":
                    EventTask e = new EventTask(desc, deadlinePart);
                    tasks.add(e);

                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print(" \n");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("       " + e.toString());
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print("  ");
                    for (int i = 0; i < 50; i++) {
                        System.out.print("═");
                    }
                    System.out.print(" \n");
                    System.out.print(" \n");
                    break;

                default:
                    Task newTask = new Task(input);
                    tasks.add(newTask);

                    // Formatting output
                    int padding = 5;
                    int width = input.length() + padding + 8;
                    System.out.print("  ╭");
                    for (int i = 0; i < width; i++) {
                        System.out.print("─");
                    }
                    System.out.print("╮ \n");
                    System.out.print("  │ added: " + input + "     │ \n");
                    System.out.print("  ╰");
                    for (int i = 0; i < width; i++) {
                        System.out.print("─");
                    }
                    System.out.print("╯ \n");
                    System.out.print(" \n");
                    break;
            }
        }
    }
}
