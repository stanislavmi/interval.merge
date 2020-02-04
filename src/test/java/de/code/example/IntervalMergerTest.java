package de.code.example;

import de.code.example.object.Interval;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class IntervalMergerTest {
    List intervalList = List.of(
            new Interval(25, 30),
            new Interval(2, 19),
            new Interval(14, 23),
            new Interval(4, 8));

    public IntervalMergerTest() throws Exception {
    }

    @Test
    public void testTreeMap() throws Exception {
        assertNotEquals(intervalList, new IntervalMerger().mergeTreeMap(intervalList));
    }

    @Test
    public void testList() throws Exception {
        assertNotEquals(intervalList, new IntervalMerger().mergeList(intervalList));
    }

    @Test
    public void testListWithTreeMap() throws Exception {
        assertEquals(new IntervalMerger().mergeList(intervalList), new IntervalMerger().mergeTreeMap(intervalList));
    }

    @Test
    public void testDurationExecutionTimeForListAndTreeMap() throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("Input " + intervalList + "\n");
        IntervalMerger intervalMerger = new IntervalMerger();
        System.out.println("Result with List " + intervalMerger.mergeList(intervalList));
        System.out.println("Duration time with List solution " + (System.currentTimeMillis() - start) + " Millis\n");
        start = System.currentTimeMillis();
        System.out.println("Result time with TreeMap " + intervalMerger.mergeTreeMap(intervalList));
        System.out.println("Duration time with TreeMap solution " + (System.currentTimeMillis() - start) + " Millis\n");
    }

}
