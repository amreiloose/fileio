package loose;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This class is used to write the word count results into a txt file.
 */
public class WordCountWriter {

    BufferedWriter bufferedWriter;

    /**
     * This constructor takes the path for the output file and creates a File and a Buffered Writer from this
     * to start writing to the File.
     *
     * @param outputFilePath The path to the output file.
     */
    public WordCountWriter (String outputFilePath) {
        bufferedWriter = null;
        try {
            File outputFile = new File(outputFilePath);
            bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes the given wordCounts into the Buffered Writer
     *
     * @param wordCounts the words that are written to the output file with their count
     * @return a list of wordCounts.
     */
    public void writeWordCounts (LinkedList<WordCount> wordCounts) {
        for (WordCount wordCount : wordCounts) {
            try {
                bufferedWriter.write(wordCount.getWord() + ": " + wordCount.getCount());
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        closeBufferedWriter();
    }

    /**
     * This method closes the Buffered Writer when it is no longer required
     **/
    private void closeBufferedWriter() {
        try {
            bufferedWriter.close();
        } catch (Exception e) {

        }
    }

}
