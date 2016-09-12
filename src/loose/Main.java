package loose;
/**
 * This class is used to start the program
 */
public class Main {

    /**
     * This method starts the program.
     *
     * @param arguments an array of console arguments that should first contain the inputPath, then the outputPath
     */
    public static void main(String[] arguments) {
        if (arguments.length >= 2) {
            OccurrenceCounter occurrenceCounter = new OccurrenceCounter(arguments[0], arguments[1]);
        } else {
            System.out.println("Please provide the arguments for this function in the format Main inputPath outputPath");
        }
    }
}
