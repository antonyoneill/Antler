/**
 * 
 */
package tech.antonyoneill.antler.entity;

import java.time.Instant;

import tech.antonyoneill.antler.utils.ReverseInstantComparator;

/**
 * Defines the interface for elements that could be sorted by the {@link ReverseInstantComparator}
 * 
 * @author @antonyoneill
 *
 */
public interface HasCreatedInstant {

    /**
     * @return The {@link Instant} the entity was created
     */
    public Instant getCreatedInstant();
    
}
