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
        if (this.isDone) {
            return "T | 1 | " + this.description;
        }
        return "T | 0 | " + this.description;

    }
}
