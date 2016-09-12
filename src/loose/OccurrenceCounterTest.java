package loose;

import org.junit.Test;
import org.mockito.Mockito;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class OccurrenceCounterTest {
    @Test
    public void test_simplify_String_with_exclamation_mark() throws Exception {
        String simplifiedString = OccurrenceCounter.simplifyString("Hello!");
        assertEquals("hello", simplifiedString);
    }

    @Test
    public void test_simplify_String_with_several_punctuation_marks() throws Exception {
        String simplifiedString = OccurrenceCounter.simplifyString("...Hello?");
        assertEquals("hello", simplifiedString);
    }

    @Test
    public void test_count_words() throws Exception {
        WordReader wordReaderMock = Mockito.mock(WordReader.class);
        Mockito.when(wordReaderMock.readWord())
                .thenReturn("Hello! ","This", "is", "a", "simple", "text", "to", "test","this", "method.", null);
        LinkedList<WordCount> wordCountList = OccurrenceCounter.countWords(wordReaderMock);
        assertEquals(9, wordCountList.size());
        assertEquals("a", wordCountList.get(0).getWord());
        assertEquals(1, wordCountList.get(0).getCount());
        assertEquals("hello", wordCountList.get(1).getWord());
        assertEquals(1, wordCountList.get(1).getCount());
        assertEquals("is", wordCountList.get(2).getWord());
        assertEquals(1, wordCountList.get(2).getCount());
        assertEquals("method", wordCountList.get(3).getWord());
        assertEquals(1, wordCountList.get(3).getCount());
        assertEquals("simple", wordCountList.get(4).getWord());
        assertEquals(1, wordCountList.get(4).getCount());
        assertEquals("test", wordCountList.get(5).getWord());
        assertEquals(1, wordCountList.get(5).getCount());
        assertEquals("text", wordCountList.get(6).getWord());
        assertEquals(1, wordCountList.get(6).getCount());
        assertEquals("this", wordCountList.get(7).getWord());
        assertEquals(2, wordCountList.get(7).getCount());
        assertEquals("to", wordCountList.get(8).getWord());
        assertEquals(1, wordCountList.get(8).getCount());
    }

    @Test
    public  void test_get_sorted_List_of_word_counts() {
        WordReader wordReaderMock = Mockito.mock(WordReader.class);
        Mockito.when(wordReaderMock.readWord())
                .thenReturn("three ","one", "Two", "one", "two", null);
        LinkedList<WordCount> sortedWordCountList = OccurrenceCounter.getSortedListOfWordCounts(wordReaderMock);
        assertEquals(3, sortedWordCountList.size());
        assertEquals("one", sortedWordCountList.get(0).getWord());
        assertEquals(2, sortedWordCountList.get(0).getCount());
        assertEquals("two", sortedWordCountList.get(1).getWord());
        assertEquals(2, sortedWordCountList.get(1).getCount());
        assertEquals("three", sortedWordCountList.get(2).getWord());
        assertEquals(1, sortedWordCountList.get(2).getCount());
    }



}