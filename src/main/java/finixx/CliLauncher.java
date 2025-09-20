package finixx;

import java.util.Scanner;

/**
 * Simple CLI launcher for Finixx to support A-TextUiTesting.
 * This runs alongside the GUI version, but is only used for automated tests.
 */
public class CliLauncher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Finixx finixx = new Finixx("text-ui-test/data/test_tasks.txt"); // use a test file
        boolean isRunning = true;

        while (isRunning && sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            String response = finixx.getResponse(input);
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println(response);
            System.out.println("-------------------------------------------------------------------------------------");

            if (input.equalsIgnoreCase("bye")) {
                isRunning = false;
            }
        }
        sc.close();
    }
}
