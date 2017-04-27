package tech.antonyoneill.antler.command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

import tech.antonyoneill.antler.AntlerApplication;

public class CommandTest {

    AntlerApplication                     app        = new AntlerApplication(System.console());
    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}
