package baymaxx.task;

/**
 * Represents a task that occurs at a specific time or event.
 * Extends the abstract Task class by adding an argument (e.g., event time or
 * location).
 * Stores the description, completion status, and additional event information.
 */
public class EventTask extends Task {
        private String arg;

        /**
         * Constructs an EventTask.
         * 
         * @param description Description of the event task
         * @param isDone      Completion status of the task
         * @param arg         Event details in "command1 time1/command2 time2" format
         */
        public EventTask(String description, boolean isDone, String arg) {
                super(description, isDone);
                this.arg = arg;
        }

        /**
         * Returns a string representation of this EventTask.
         * 
         * @return String representation for display
         */
        @Override
        public String toString() {
                return "[E]" + super.toString() + "(" + formatEvent() + ")";
        }

        private String formatEvent() {
                assert arg != null : "Event argument should not be null";
                String[] timeParts = arg.split("/", 2);
                assert timeParts.length >= 1 : "Event argument should contain at least one '/' separator";

                String from = timeParts[0];
                String[] fromParts = from.split(" ", 2);
                assert fromParts.length == 2 : "From part should contain command and time separated by a space";
                String fromCommand = fromParts[0];
                String fromTime = fromParts[1];

                String to = (timeParts.length > 1) ? timeParts[1] : "";
                String[] toParts = to.split(" ", 2);
                assert toParts.length == 2 : "To part should contain command and time separated by a space";
                String toCommand = toParts[0];
                String toTime = toParts[1];

                return fromCommand + ": " + fromTime + " " + toCommand + ": " + toTime;
        }

        /**
         * Converts this EventTask into a string suitable for saving to a file.
         * 
         * @return a string representing the EventTask for storage
         */
        @Override
        public String toSaveFormatString() {
                return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + arg;
        }
}
