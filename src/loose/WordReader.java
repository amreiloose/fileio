package loose;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class reads a text from a txt file word by word.
 */
public class WordReader {

    /**
     * The buffered Reader is used to read the file.
     */
    BufferedReader bufferedReader;
    /**
     * The current Line contains the line of text that is currently read.
     */
    LinkedList<String> currentLine;

    /**
     * This class is used to perform read operations of single words on the given file.
     *
     * @param  inputFilePath The path to the input file that is being used.
     */
    public WordReader(String inputFilePath) {
        try {
            FileReader fileToRead = new FileReader(inputFilePath);
            bufferedReader = new BufferedReader(fileToRead);
        } catch (FileNotFoundException e) {
            System.out.println("The specified input file was not found.");
        }
        currentLine = new LinkedList<>();
    }

    /**
     * This method is used to return the next word from the input stream.
     *
     * When read, the word will be removed from the current line.
     *
     * @return the next Word from the Buffer if its not empty, null otherwise.
     */
    public String readWord() {
        if (!isEmpty()) {
            return currentLine.remove();
        } else {
            return null;
        }
    }

    /**
     * This method is used to determine whether the input buffer is empty.
     *
     * @return true if the Buffer is empty and no new word can be read, false otherwise.
     */
    boolean isEmpty() {
        if (currentLine.size() == 0) {
            if (!readNextLine()) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to read the next line from the input buffer.
     *
     * @return true if the next Line was successfully read, false otherwise.
     */
    boolean readNextLine() {
        try {
            String nextLine = bufferedReader.readLine();
            if (nextLine != null) {
                if (isLineEmpty(nextLine)) {
                    return readNextLine();
                } else {
                    currentLine = splitLineIntoWords(nextLine);
                    return true;
                }
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method is used to split a line into the single words.
     *
     * @param  line a line of text that is supposed to be split into words.
     * @return a linked list of the split words.
     */
    static LinkedList<String> splitLineIntoWords(String line) {
        return new LinkedList<String>(Arrays.asList(line.split(" ")));
    }

    /**
     * This method is used to determine whether the given line is empty
     *
     * @param  line The line that is checked.
     * @return true if the given line is empty, false otherwise.
     */
    static boolean isLineEmpty (String line) {
        line = line.replaceAll("\\r|\\n", "").trim();
        return line.equals("");
    }

}
