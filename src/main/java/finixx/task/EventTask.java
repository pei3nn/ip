package finixx.task;

/**
 * Represents a task that occurs at a specific time or event.
 * Extends the abstract Task class by adding an argument (e.g., event time or
 * location).
 * Stores the description, completion status, and additional event information.
 */
public class EventTask extends Task {
        private String start;
        private String end;

        /**
         * Constructs an EventTask.
         * 
         * @param description Description of the event task
         * @param isDone      Completion status of the task
         * @param start       Start time of the event
         * @param end         End time of the event
         */
        public EventTask(String description, boolean isDone, String note, String start, String end) {
                super(description, isDone, note);
                this.start = start;
                this.end = end;
        }

        /**
         * Returns a string representation of this EventTask.
         * 
         * @return String representation for display
         */
        @Override
        public String toString() {
                return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")"
                        + (getNote().isEmpty() ? "" : " <" + getNote() + ">");
        }

        /**
         * Returns the start time of this event.
         *
         * @return the start time as a string
         */
        public String getStart() {
                return this.start;
        }

        /**
         * Returns the end time of this event.
         *
         * @return the end time as a string
         */
        public String getEnd() {
                return this.end;
        }

        /**
         * Converts this EventTask into a string suitable for saving to a file.
         * 
         * @return a string representing the EventTask for storage
         */
        @Override
        public String toSaveFormatString() {
                return "E | " + (isDone() ? "1" : "0") + " | "
                        + getDescription() + " | " + start + " | " + end + " | " + getNote();
        }
}
