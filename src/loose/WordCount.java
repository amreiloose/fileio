package loose;
/**
 * This class stores a Word and how often it was found in the text.
 */
public class WordCount implements Comparable<WordCount>{
    /**
     * This is the word this Word Count is for.
     */
    private String word;
    /**
     * This stores at which position in the text the word first occurred
     */
    private Integer firstOccurence;
    /**
     * This stores how often the word was found in the text
     */
    private int count;

    public WordCount(String word, Integer firstOccurence) {
        this.word = word;
        this.firstOccurence = firstOccurence;
        this.count = 1;
    }

    /**
     * Increases the count for this word by one.
     */
    public void increaseCount() {
        count ++;
    }

    /**
     * Returns the word.
     * @return the word string.
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns the count.
     * @return the count as an int.s
     */
    public int getCount() {
        return count;
    }


    /**
     * This method is used to create a String representation of the WordCount
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return count + " X " + word;
    }

    /**
     * This compares two WordCounts and
     *
     * @param otherWord the word count this is compared to.
     */
    @Override
    public int compareTo(WordCount otherWord) {
        if (this.count > otherWord.count) {
            return -1;
        } else if (this.count < otherWord.count) {
            return 1;
        }
        return this.firstOccurence.compareTo(otherWord.firstOccurence);
    }
}
