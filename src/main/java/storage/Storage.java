package storage;

import task.Task;
import task.TaskCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public TaskCollection loadTasks() {
        TaskCollection taskList = new TaskCollection();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                Task task = Task.fromSaveFormat(line);
                taskList.addTask(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return taskList;
    }

    public void saveTasks(TaskCollection tasks) {
        try {
            Files.write(filePath, tasks.toSaveFormat());
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
