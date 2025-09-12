package baymaxx.task;

/**
 * Represents a generic task with a description and completion status.
 * Serves as the base class for specific task types such as Todo, Deadline, and Event.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description and completion status.
     * @param description Description of the task
     * @param isDone True if the task is completed, false otherwise
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing completion.
     * @return "X" if done, otherwise a space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     * @return Task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether this task is done.
     * @return True if done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of this task for display.
     * @return String representation of the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Converts this task into a string suitable for saving to a file.
     * @return String for file storage, or null if not implemented
     */
    public String toSaveFormatString() {
        return null;
    }

    /**
     * Creates a Task object from its saved string format.
     * @param line The line from the save file
     * @return The corresponding Task object
     * @throws IllegalArgumentException If the task type is unknown
     */
    public static Task fromSaveFormat(String line) {
        assert line != null : "Input line should not be null";
        String[] parts = line.trim().split(" \\| ");

        assert parts.length >= 3 : "Save format line should have at least 3 parts";
        String taskType = parts[0];
        String isDone = parts[1];
        String desc = parts[2];
        String by = parts.length > 3 ? parts[3] : "" ;

        switch (taskType) {
        case ("T"):
            return new TodoTask(desc, (Integer.parseInt(isDone) == 1));
        case ("D"):
            return new DeadlineTask(desc, (Integer.parseInt(isDone) ==1), by);
        case ("E"):
            return new EventTask(desc, (Integer.parseInt(isDone) == 1), by);
        default:
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }
}
