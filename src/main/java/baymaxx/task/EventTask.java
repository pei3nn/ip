package baymaxx.task;

/**
 * Represents a task that occurs at a specific time or event.
 * Extends the abstract Task class by adding an argument (e.g., event time or location).
 * Stores the description, completion status, and additional event information.
 */
public class EventTask extends Task {
    private String arg;
    public EventTask(String description, boolean isDone, String arg) {
        super(description, isDone);
        this.arg = arg;
    }

    /**
     * Returns a string representation of this EventTask
     */
    @Override
    public String toString() {
        String[] timeParts = arg.split("/", 2);
        String fromTime = timeParts[0];
        String[] parts1 = fromTime.split(" ", 2);
        String command1 = parts1[0];
        String time1 = parts1[1];
        String toTime = (timeParts.length > 1) ? timeParts[1] : "";
        String[] parts2 = toTime.split(" ", 2);
        String command2 = parts2[0];
        String time2 = parts2[1];

        return "[E]" + super.toString()
                + "(" + command1 + ": " + time1
                + command2 + ": " + time2 + ")";
    }

    /**
     * Converts this EventTask into a string suitable for saving to a file.
     * @return a string representing the EventTask for storage
     */
    @Override
    public String toSaveFormatString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + arg;
    }
}
