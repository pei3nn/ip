package finixx.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 * Extends the abstract Task class by adding a deadline field.
 * Stores the description, completion status, and deadline of the task.
 */
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Constructs a DeadlineTask with the specified description, completion status,
     * and deadline.
     * 
     * @param description the description of the task
     * @param isDone      the completion status of the task
     * @param deadline    the deadline of the task in "yyyy-MM-dd" format
     */
    public DeadlineTask(String description, boolean isDone, String note, String deadline) {
        super(description, isDone, note);
        assert deadline != null && !deadline.isEmpty() : "Deadline cannot be null or empty";
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of this task.
     *
     * @return the deadline as a string
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Returns a string representation of this DeadlineTask
     * 
     * @return a string representation of the DeadlineTask
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadline() + ")"
                + (getNote().isEmpty() ? "" : " <" + getNote() + ">");
    }

    private String formatDeadline() {
        LocalDate parsedDate = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Converts this DeadlineTask into a string suitable for saving to a file.
     * 
     * @return a string representing the DeadlineTask for storage
     */
    @Override
    public String toSaveFormatString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + deadline + " | " + getNote();
    }
}
