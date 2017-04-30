package tech.antonyoneill.antler.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import tech.antonyoneill.antler.AntlerApplication;
import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.tests.StreamTest;
import tech.antonyoneill.antler.utils.PostPrinter;
import tech.antonyoneill.antler.utils.PostPrinterImpl;

public class CommandTest extends StreamTest {

    AntlerApplication app;
    MockPostPrinter   printer;

    public void setupApp() {
        printer = new MockPostPrinter();
        app = new AntlerApplication(System.console(), printer);
    }

    class MockPostPrinter implements PostPrinter {

        Collection<Post> posts;
        Exception        exception;

        @Override
        public void printPosts(Collection<Post> posts) {
            this.posts = posts;
        }

        @Override
        public void printException(Exception exception) {
            this.exception = exception;
        }

        public Post[] getPostsPrinted() {
            return posts.toArray(new Post[posts.size()]);
        }

        public Exception getException() {
            return exception;
        }
    }
}
