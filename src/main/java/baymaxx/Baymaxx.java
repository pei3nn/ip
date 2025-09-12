package baymaxx;

import baymaxx.parser.Parser;
import baymaxx.storage.Storage;
import baymaxx.task.TaskCollection;
import baymaxx.ui.Ui;

import java.io.IOException;

/**
 * The main class for the Baymaxx chatbot application.
 * Handles initialization of storage, task collection, and user interface.
 */
public class Baymaxx {
    private Storage storage;
    private TaskCollection tasks;
    private Ui ui;

    /**
     * Constructs a Baymaxx instance with the specified file path for storage.
     * Initializes the UI, storage, and loads tasks from the file.
     * @param filePath Path to the storage file
     */
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

        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert tasks != null : "TaskCollection should not be null";
    }

    /**
     * Processes user input and returns the corresponding response string.
     * @param input The user input string
     * @return Response string to be displayed in the GUI
     */
    public String getResponse(String input) {
        return Parser.parse(input, ui, tasks, storage);
    }
}
