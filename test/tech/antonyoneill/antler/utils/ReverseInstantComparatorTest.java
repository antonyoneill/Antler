package tech.antonyoneill.antler.utils;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Test;

import tech.antonyoneill.antler.entity.HasCreatedInstant;

public class ReverseInstantComparatorTest {

    private ReverseInstantComparator comparator = new ReverseInstantComparator();

    @Test
    public void testLeftEarlier() {
        EntityForTest left = new EntityForTest(Instant.now().minusSeconds(60));
        EntityForTest right = new EntityForTest(Instant.now());
        
        assertEquals("Left is greater than right", 1, comparator.compare(left, right));   
    }
    
    @Test
    public void testRightEarlier() {
        EntityForTest left = new EntityForTest(Instant.now());
        EntityForTest right = new EntityForTest(Instant.now().minusSeconds(60));
        
        assertEquals("Left is less than right", -1, comparator.compare(left, right));   
    }
    
    @Test
    public void testEqual() {
        Instant instant = Instant.now();
        EntityForTest left = new EntityForTest(instant);
        EntityForTest right = new EntityForTest(instant);
        
        assertEquals("Left is equal to right", 0, comparator.compare(left, right));   
    }

    private class EntityForTest implements HasCreatedInstant {
        private Instant createdInstant;

        public EntityForTest(Instant instant) {
            this.createdInstant = instant;
        }

        public Instant getCreatedInstant() {
            return createdInstant;
        }
    }
}
