package baymaxx;

import baymaxx.parser.Parser;
import baymaxx.storage.Storage;
import baymaxx.task.TaskCollection;
import baymaxx.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Baymaxx {
    private Storage storage;
    private TaskCollection tasks;
    private Ui ui;

    public Baymaxx(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = loadsTaskFromStorage(storage);
    }

    private TaskCollection loadsTaskFromStorage(Storage storage) {
        try {
            TaskCollection tasks = storage.loadTasks();
            ui.showLoadingSuccess();
            return tasks;
        } catch (IOException e) {
            ui.showLoadingError();
            return new TaskCollection();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return Parser.parse(input, ui, tasks, storage);
    }
}
