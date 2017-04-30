package tech.antonyoneill.antler.utils;

import java.util.Collection;

import tech.antonyoneill.antler.entity.Post;

public interface PostPrinter {

    /**
     * Print the posts
     * 
     * @param posts
     */
    void printPosts(Collection<Post> posts);

    /**
     * Print the exception
     * 
     * @param exception
     */
    public void printException(Exception exception);
}