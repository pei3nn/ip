package baymaxx.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    String deadline;
    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String[] parts = deadline.split(" ", 2);
        String command = parts[0];

        // Transforming Dates and Time
        String date = parts[1];
        String by;
        LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        by = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));


        return "[D]" + super.toString() + "(" + command + ": " + by + ")";
    }

    @Override
    public String toSaveFormatString() {
        if (this.isDone) {
            return "D | 1 | " + this.description + " | " + this.deadline;
        }
        return "D | 0 | " + this.description + " | " + this.deadline;

    }
}
