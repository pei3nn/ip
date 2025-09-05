package baymaxx.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 * Extends the abstract Task class by adding a deadline field.
 * Stores the description, completion status, and deadline of the task.
 */
public class DeadlineTask extends Task {
    private String deadline;
    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of this DeadlineTask
     */
    @Override
    public String toString() {
        String[] parts = deadline.split(" ", 2);
        String command = parts[0];

        // Transforming Dates and Time
        String date = parts[1];
        String by;
        LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        by = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
//        return "[D]" + super.toString() + "(by: " + by + ")";

        return "[D]" + super.toString() + "(" + command + ": " + by + ")";
    }

    /**
     * Converts this DeadlineTask into a string suitable for saving to a file.
     * @return a string representing the DeadlineTask for storage
     */
    @Override
    public String toSaveFormatString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
    }
}
