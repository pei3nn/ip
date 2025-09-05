package baymaxx.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Works as an entry point to our application
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

