package baymaxx.task;

/**
 * Represents a simple to-do task without a specific deadline or event time.
 * Extends the abstract Task class by storing only a description and completion status.
 */
public class TodoTask extends Task {
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of this TodoTask
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts this TodoTask into a string suitable for saving to a file.
     * @return a string representing the TodoTask for storage
     */
    @Override
    public String toSaveFormatString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
