import task.Task;

public class DeadlineTask extends Task {
    String deadline;
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String[] parts = deadline.split(" ", 2);
        String command = parts[0];
        String date = parts[1];
        return "[D]" + super.toString() + "(" + command + ": " + date + ")";
    }
}
