package loose;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * This class is used for testing the WordReader
 */
public class WordReaderTest {
    @Test
    public void test_read_single_Word() throws Exception {
        BufferedReader readerMock = Mockito.mock(BufferedReader.class);
        Mockito.when(readerMock.readLine()).thenReturn("This is a very small text.");

        WordReader testReader = new WordReader("");
        testReader.bufferedReader = readerMock;
        String readWord = testReader.readWord();

        assertEquals("This", readWord);
    }

    @Test
    public void test_read_two_lines() throws IOException {
        BufferedReader readerMock = Mockito.mock(BufferedReader.class);
        Mockito.when(readerMock.readLine()).thenReturn("line 1", "line 2");

        WordReader testReader = new WordReader("");
        testReader.bufferedReader = readerMock;
        String readWord = testReader.readWord();
        assertEquals("line", readWord);
        readWord = testReader.readWord();
        assertEquals("1", readWord);
        readWord = testReader.readWord();
        assertEquals("line", readWord);
        readWord = testReader.readWord();
        assertEquals("2", readWord);
    }

    @Test
    public void test_read_with_empty_buffer_returns_null() throws IOException {
        BufferedReader readerMock = Mockito.mock(BufferedReader.class);
        Mockito.when(readerMock.readLine()).thenReturn(null);

        WordReader testReader = new WordReader("");
        testReader.bufferedReader = readerMock;
        String readWord = testReader.readWord();
        assertEquals(null, readWord);
    }

    @Test
    public void test_split_line_into_Words() {
        LinkedList<String> words = WordReader.splitLineIntoWords("This is a line.");
        assertEquals("This",words.get(0));
        assertEquals("is",words.get(1));
        assertEquals("a",words.get(2));
        assertEquals("line.",words.get(3));
    }

    @Test
    public void test_is_line_empty_with_words_with_preceding_whitespaces() {
        boolean isLineEmpty = WordReader.isLineEmpty("   not empty ");
        assertEquals(false, isLineEmpty);
    }

    @Test
    public void test_is_line_empty_with_only_whitespaces() {
        boolean isLineEmpty = WordReader.isLineEmpty("    ");
        assertEquals(true, isLineEmpty);
    }

    @Test
    public void test_is_line_empty_with_whitespaces_and_linebreaks() {
        boolean isLineEmpty = WordReader.isLineEmpty("     \r ");
        assertEquals(true, isLineEmpty);
    }

    @Test
    public void test_read_next_line_with_4_words() throws IOException {
        BufferedReader readerMock = Mockito.mock(BufferedReader.class);
        Mockito.when(readerMock.readLine()).thenReturn("This is a line.");

        WordReader testReader = new WordReader("");
        testReader.bufferedReader = readerMock;
        testReader.readNextLine();
        assertEquals("This", testReader.currentLine.get(0));
        assertEquals("is", testReader.currentLine.get(1));
        assertEquals("a", testReader.currentLine.get(2));
        assertEquals("line.", testReader.currentLine.get(3));
    }

    @Test
    public void test_read_next_line_with_preceding_empty_line() throws IOException {
        BufferedReader readerMock = Mockito.mock(BufferedReader.class);
        Mockito.when(readerMock.readLine()).thenReturn("   ", "This is a line.");

        WordReader testReader = new WordReader("");
        testReader.bufferedReader = readerMock;
        testReader.readNextLine();
        assertEquals("This", testReader.currentLine.get(0));
        assertEquals("is", testReader.currentLine.get(1));
        assertEquals("a", testReader.currentLine.get(2));
        assertEquals("line.", testReader.currentLine.get(3));
    }

    @Test
    public void test_is_Buffer_empty_with_current_line_not_empty() throws IOException {
        BufferedReader readerMock = Mockito.mock(BufferedReader.class);
        Mockito.when(readerMock.readLine()).thenReturn("This is a line.");

        WordReader testReader = new WordReader("");
        testReader.bufferedReader = readerMock;

        testReader.readNextLine();
        assertEquals(4, testReader.currentLine.size());
        boolean isEmpty = testReader.isEmpty();
        assertEquals(false, isEmpty);
    }

    @Test
    public void test_is_Buffer_empty_with_new_line_being_read() throws IOException {
        BufferedReader readerMock = Mockito.mock(BufferedReader.class);
        Mockito.when(readerMock.readLine()).thenReturn("This is a line.");

        WordReader testReader = new WordReader("");
        testReader.bufferedReader = readerMock;

        assertEquals(0, testReader.currentLine.size());
        boolean isEmpty = testReader.isEmpty();
        assertEquals(4, testReader.currentLine.size());
        assertEquals(false, isEmpty);
    }


    @Test
    public void test_is_Buffer_empty_with_buffered_reader_returning_null() throws IOException {
        BufferedReader readerMock = Mockito.mock(BufferedReader.class);
        Mockito.when(readerMock.readLine()).thenReturn(null);

        WordReader testReader = new WordReader("");
        testReader.bufferedReader = readerMock;

        assertEquals(0, testReader.currentLine.size());
        boolean isEmpty = testReader.isEmpty();
        assertEquals(true, isEmpty);
    }


}