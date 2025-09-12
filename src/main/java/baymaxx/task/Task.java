package baymaxx.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done baymaxx.task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    public String toSaveFormatString() {
        return null;
    }

    public static Task fromSaveFormat(String line) {
        String[] parts = line.trim().split(" \\| ");
        String taskType = parts[0];
        String isDone = parts[1];
        String desc = parts[2];
        String by = parts.length > 3 ? parts[3] : "" ;

        switch (taskType) {
        case ("T"):
            return new TodoTask(desc, (Integer.parseInt(isDone) == 1));
        case ("D"):
            return new DeadlineTask(desc, (Integer.parseInt(isDone) ==1), by);
        case ("E"):
            return new EventTask(desc, (Integer.parseInt(isDone) == 1), by);
        default:
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }
}
