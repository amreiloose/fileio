package loose;

import java.util.LinkedList;
import java.util.TreeMap;

/**
 * This class is used to calculate how often each word occurs in a text.
 */
public class OccurrenceCounter {

    /**
     * This constructor takes the path for the input file and the path for the output file.
     * It then counts the words in the input file and writes the words into an output file
     * with the count of their occurence.
     *
     * @param inputFileName the path to the input file.
     * @param outputFilePath the path to the output file.
     */
    public OccurrenceCounter(String inputFileName, String outputFilePath) {
        WordReader wordReader = new WordReader(inputFileName);
        WordCountWriter wordCountWriter = new WordCountWriter(outputFilePath);
        LinkedList<WordCount> sortedWordCountList = getSortedListOfWordCounts(wordReader);
        wordCountWriter.writeWordCounts(sortedWordCountList);

    }

    /**
     * This method calculates the sorted list of words depending on their occurrence in the input file.
     *
     * @param wordReader the reader used to read the words from the input file.
     * @return a list of wordCounts sorted by their count.
     */
    static LinkedList<WordCount> getSortedListOfWordCounts(WordReader wordReader) {
        LinkedList<WordCount> wordCountList = countWords(wordReader);
        wordCountList.sort(null);
        return wordCountList;
    }

    /**
     * This method calculates a list of all the word counts from an input file.
     *
     * @param wordReader the reader used to read the words from the input file.
     * @return a list of wordCounts.
     */
    static LinkedList<WordCount> countWords (WordReader wordReader) {
        TreeMap<String, WordCount> wordCountMap = new TreeMap<String, WordCount>();
        String currentWord;
        Integer positionInText = 0;
        while ((currentWord = wordReader.readWord()) != null) {
            currentWord = simplifyString(currentWord);
            if (!wordCountMap.containsKey(currentWord)) {
                wordCountMap.put(currentWord, new WordCount(currentWord, positionInText));
            } else {
                wordCountMap.get(currentWord).increaseCount();
            }
            positionInText++;
        }
        return new LinkedList<WordCount>(wordCountMap.values());
    }

    /**
     * This method removes all punctuation marks and whitespaces from the word and changes it to lower case,
     * making it easier to compare the words with each other.
     *
     * @param word the word that is supposed to be simplified.
     * @return the word in lower case without punctuation marks.
     */
    static String simplifyString(String word) {
        return word.toLowerCase()
                .replace(",","")
                .replace(".","")
                .replace(":","")
                .replace("!","")
                .replace("?","")
                .replace(";","")
                .trim();
    }
}
