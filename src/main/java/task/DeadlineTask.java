package task;

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
        String date = parts[1];
        return "[D]" + super.toString() + "(" + command + ": " + date + ")";
    }

    @Override
    public String toSaveFormatString() {
        if (this.isDone) {
            return "D | 1 | " + this.description + " | " + this.deadline;
        }
        return "D | 0 | " + this.description + " | " + this.deadline;

    }
}
