package finixx.task;

/**
 * Represents a generic task with a description and completion status.
 * Serves as the base class for specific task types such as Todo, Deadline, and Event.
 */
public class Task {
    private String description;
    private boolean isDone;
    private String note;

    /**
     * Constructs a Task with the specified description and completion status.
     * @param description Description of the task
     * @param isDone True if the task is completed, false otherwise
     * @param note Additional notes of the task
     */
    public Task(String description, boolean isDone, String note) {
        this.description = description;
        this.isDone = isDone;
        this.note = note;
    }

    /**
     * Returns the status icon representing completion.
     * @return "X" if done, otherwise a space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     * @return Task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether this task is done.
     * @return True if done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the additional notes to this task
     * @return Task notes
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Adds note to this task
     * @param note Task notes
     */
    public void addNote(String note) {
        this.note = note;
    }

    /**
     * Returns a string representation of this task for display.
     * @return String representation of the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Converts this task into a string suitable for saving to a file.
     * @return String for file storage, or null if not implemented
     */
    public String toSaveFormatString() {
        return null;
    }

    /**
     * Creates a Task object from its saved string format.
     * @param line The line from the save file
     * @return The corresponding Task object
     * @throws IllegalArgumentException If the task type is unknown
     */
    public static Task fromSaveFormat(String line) {
        assert line != null : "Input line should not be null";
        String[] parts = line.split("\\s*\\|\\s*");

        assert parts.length >= 3 : "Save format line should have at least 3 parts";
        String taskType = parts[0];
        String isDone = parts[1];
        String desc = parts[2];

        switch (taskType) {
        case ("T"): // T | 1 | read book | Harry Potter
            String todoNote = parts.length > 3 ?parts[3] : "";
            return new TodoTask(desc, (Integer.parseInt(isDone) == 1), todoNote);

        case ("D"): // D | 0 | return book | by 2025-10-30 | National Library
            String by = parts[3];
            String deadlineNote = parts.length > 4 ?parts[4] : "";
            return new DeadlineTask(desc, (Integer.parseInt(isDone) ==1), deadlineNote, by);

        case ("E"): // E | 0 | proj meeting  | from Mon 2pm /to 5pm | CS2103T
            String startTime = parts[3];
            String endTime = parts[4];
            String eventNote = parts.length > 5 ?parts[5] : "";
            return new EventTask(desc, (Integer.parseInt(isDone) == 1), eventNote, startTime, endTime);

        default:
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }
}
