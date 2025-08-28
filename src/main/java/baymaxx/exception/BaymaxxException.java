package baymaxx.exception;

public class BaymaxxException extends Exception {

    public BaymaxxException(String message) {
        super(message);
    }

    public void printMessage() {
        System.out.print("  ");
        for (int i = 0; i < 30; i++) {
            System.out.print("..");
        }
        System.out.print("\n");
        System.out.println("  " + this.getMessage());
        System.out.print("  ");
        for (int i = 0; i < 30; i++) {
            System.out.print("..");
        }
        System.out.print("\n");
        System.out.print("\n");
    }

}
