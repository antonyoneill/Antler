package tech.antonyoneill.antler.tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;

import tech.antonyoneill.antler.utils.TimeUtil;

public class StreamTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    public String[] getOutContent() {
        return trimArray(outContent.toString().split("\n"));
    }

    public String[] getErrContent() {
        return trimArray(errContent.toString().split("\n"));
    }
    
    private String[] trimArray(String[] input) {
        return Arrays.stream(input).map(line -> line.trim()).toArray(size -> new String[size]);
    }
    
    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}
