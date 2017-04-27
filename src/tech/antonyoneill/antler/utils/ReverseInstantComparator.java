/**
 * 
 */
package tech.antonyoneill.antler.utils;

import java.time.Instant;
import java.util.Comparator;

import tech.antonyoneill.antler.entity.HasCreatedInstant;

/**
 * This {@link Comparator} will sort {@link HasCreatedInstant} implementations
 * by their createdInstant descending, yeilding a list that is in reverse
 * chronological order
 * 
 * @author @antonyoneill
 *
 */
public class ReverseInstantComparator implements Comparator<HasCreatedInstant> {

    @Override
    public int compare(HasCreatedInstant left, HasCreatedInstant right) {
        Instant leftInstant = left.getCreatedInstant();
        Instant rightInstant = right.getCreatedInstant();
        return rightInstant.compareTo(leftInstant);
    }

}
