import java.util.Scanner;

public class Baymaxx {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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
            } else {
                int padding = 5;
                int width = input.length() + padding + 1;

                // Formatting output
                System.out.print("  ╭");
                for (int i = 0; i < width; i++) {
                    System.out.print("─");
                }
                System.out.print("╮ \n");
                System.out.print("  │ " + input + "     │ \n");
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
