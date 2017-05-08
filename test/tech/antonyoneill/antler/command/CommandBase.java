package tech.antonyoneill.antler.command;

import java.util.Collection;

import tech.antonyoneill.antler.AntlerApplication;
import tech.antonyoneill.antler.entity.Post;
import tech.antonyoneill.antler.tests.StreamBase;
import tech.antonyoneill.antler.utils.PostPrinter;

public class CommandBase extends StreamBase {

    AntlerApplication app;
    MockPostPrinter   printer;

    public void setupApp() {
        printer = new MockPostPrinter();
        app = new AntlerApplication(System.console(), printer);
    }

    //TODO: Use Mockito here
    class MockPostPrinter extends PostPrinter {

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
