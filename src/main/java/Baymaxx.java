import parser.Parser;
import storage.Storage;
import task.TaskCollection;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Baymaxx {
    private Storage storage;
    private TaskCollection tasks;
    private Ui ui;

    public Baymaxx(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
            ui.showLoadingSuccess();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskCollection();
        }
    }

    public void run() {
        Ui.printGreeting();
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            ui.printPromptLine();
            String input = sc.nextLine().trim();

            Parser.parse(input, ui, tasks, storage);

            if (Parser.isExit) {
                isRunning = false;
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Baymaxx("data/tasks.txt").run();
    }
}
