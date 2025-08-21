import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Baymaxx {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> items = new ArrayList<>();

        String Greeting = "Hi! I'm Baymaxx (●─●) \n"
                + "Your personal chatbot assistant \n"
                + "What can I do for you? \n";
        String Exit = "Goodbye. Hope to see you again soon! (●─●) \n";
        System.out.println(Greeting);

        while (true) {
            System.out.print("Ask me (^-^): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("────────────────────────────────────────────────\n"
                        + "👋" + Exit);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                // Formatting output
                int width = 50;
                System.out.print("  ");
                for (int i = 0; i < width; i++) {
                    System.out.print("═");
                }
                System.out.print(" \n");
                for (int i = 0; i < items.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + items.get(i));
                }
                System.out.print("  ");
                for (int i = 0; i < width; i++) {
                    System.out.print("═");
                }
                System.out.print(" \n");
            } else {
                items.add(input);

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
            }
        }
        sc.close();
    }
}
