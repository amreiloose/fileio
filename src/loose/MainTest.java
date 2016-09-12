package loose;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUpOutputStream() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void test_main_with_no_output_Path() {
        String[] arguments = new String[1];
        arguments[0] = "some/Input/Path.txt";
        Main.main(arguments);
        assertTrue(outputStream.toString().trim()
                .equals("Please provide the arguments for this function in the format Main inputPath outputPath"));
    }


    @After
    public void cleanUpOutputStream() {
        System.setOut(null);
    }

}