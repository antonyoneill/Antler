/**
 * 
 */
package tech.antonyoneill.antler.utils;

import java.time.Instant;
import java.util.Comparator;

import tech.antonyoneill.antler.entity.Post;

/**
 * This {@link Comparator} will sort {@link Post}s by their createdInstant
 * descending, yeilding a list that is in reverse chronological order
 * 
 * @author @antonyoneill
 *
 */
public class ReverseInstantComparator implements Comparator<Post> {

    @Override
    public int compare(Post left, Post right) {
        Instant leftInstant = left.getCreatedInstant();
        Instant rightInstant = right.getCreatedInstant();
        return rightInstant.compareTo(leftInstant);
    }

}
