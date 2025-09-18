package finixx.task;

/**
 * Represents a simple to-do task without a specific deadline or event time.
 * Extends the abstract Task class by storing only a description and completion status.
 */
public class TodoTask extends Task {
    /**
     * Constructs a TodoTask.
     * @param description Description of the to-do task
     * @param isDone Completion status of the task
     */
    public TodoTask(String description, boolean isDone, String note) {
        super(description, isDone, note);
    }

    /**
     * Returns a string representation of this TodoTask for display.
     * @return String representation of the TodoTask
     */
    @Override
    public String toString() {
        return "[T]" + super.toString()
                + (getNote().equals("") ? "" : " <" + getNote() + ">");
    }

    /**
     * Converts this TodoTask into a string suitable for saving to a file.
     * @return String representing the TodoTask for storage
     */
    @Override
    public String toSaveFormatString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + getNote();
    }
}
