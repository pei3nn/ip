package baymaxx.task;

public class TodoTask extends Task {
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormatString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
