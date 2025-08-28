package baymaxx.storage;

import baymaxx.task.Task;
import baymaxx.task.TaskCollection;
import baymaxx.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {

    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        ensureFileExist();
    }

    public void ensureFileExist() {
        try {
            File file = filePath.toFile();
            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            Ui.printCreateFileError(e);
        }
    }

    public TaskCollection loadTasks() throws IOException {
        TaskCollection taskList = new TaskCollection();
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            Task task = Task.fromSaveFormat(line);
            taskList.addTask(task);
        }
        return taskList;
    }

    public void saveTasks(TaskCollection tasks) {
        try {
            Files.write(filePath, tasks.toSaveFormat());
        } catch (IOException e) {
            Ui.printErrorSavingTask(e);
        }
    }
}
