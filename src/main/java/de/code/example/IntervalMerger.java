package de.code.example;

import de.code.example.object.Interval;

import java.util.*;

/**
 * The IntervalMerger class implements merge overlapping intervals with two solutions:
 * 1. merge with List Collections.sort by Object Interval startPoint, @see Collections::sort
 * 2. merge with TreeMap
 *
 * @author Stanislav Michel
 */

public class IntervalMerger {

    /**
     * 1. Solution
     * Merge method, that returns result intervals as a List of Interval objects
     * implemented on List Collections.sort with Complexity O(n*log(n))
     *
     * @param intervalList - input List of intervals
     * @return resultIntervalList - List overlapping intervals
     */
    public List<Interval> mergeList(List<Interval> intervalList) {

        List<Interval> resultIntervalList = new ArrayList<>();

        if (intervalList.size() <= 1)
            return intervalList;

        List<Interval> intervalListCopy = new ArrayList(intervalList);

        // Sort of intervals by startPoint
        Collections.sort(intervalListCopy, Comparator.comparingInt(Interval::getStartPoint));

        // Get first interval as temp interval
        Interval firstInterval = new Interval(intervalListCopy.get(0).getStartPoint(), intervalListCopy.get(0).getEndPoint());

        for (int i = 1; i < intervalListCopy.size(); i++) {

            // Check if endPoint of temp interval less than startPoint of current interval
            if (firstInterval.getEndPoint() < intervalListCopy.get(i).getStartPoint()) {
                resultIntervalList.add(intervalListCopy.get(i));
                firstInterval = intervalListCopy.get(i);
            }
            // check if endPoint of temp interval less than endPoint of currentInterval
            else if (firstInterval.getEndPoint() < intervalListCopy.get(i).getEndPoint()) {
                resultIntervalList.remove(firstInterval);
                firstInterval.setEndPoint(intervalListCopy.get(i).getEndPoint());
                resultIntervalList.add(firstInterval);
            }
        }

        return resultIntervalList;
    }

    /**
     * 2. Solution
     * Merge method, implemented on TreeMap<V,U> - Ordered Red-Black tree
     * TreeMap<Integer,Integer>: key ist startPoint, value ist endPoint
     * insert complexity O(log(n))
     *
     * @param intervalList - input List of intervals
     * @return resultIntervalList - List of overlapping intervals
     */
    public List<Interval> mergeTreeMap(List<Interval> intervalList) {
        List<Interval> resultIntervalList = new ArrayList<>();

        TreeMap<Integer, Integer> intervalMap = new TreeMap<>();
        // insert key and value in TreeMap
        intervalList.forEach(a -> intervalMap.put(a.getStartPoint(), a.getEndPoint()));

        if (intervalMap.size() <= 1)
            return (intervalMap.size() == 0 ? resultIntervalList : Collections.singletonList(new Interval(intervalMap.firstKey(), intervalMap.firstEntry().getValue())));

        Map.Entry<Integer, Integer> firstEntry = intervalMap.pollFirstEntry();

        // Get first interval as temp interval
        Interval firstInterval = new Interval(firstEntry.getKey(), firstEntry.getValue());

        for (Map.Entry<Integer, Integer> entry : intervalMap.entrySet()) {

            // Check if endPoint of temp interval less than startPoint of current interval
            if (firstInterval.getEndPoint() < entry.getKey()) {
                resultIntervalList.add(new Interval(entry.getKey(), entry.getValue()));
                firstInterval = new Interval(entry.getKey(), entry.getValue());
            }

            // check if endPoint of temp interval less than endPoint of currentInterval
            else if (firstInterval.getEndPoint() < entry.getValue()) {
                resultIntervalList.remove(firstInterval);
                firstInterval.setEndPoint(entry.getValue());
                resultIntervalList.add(firstInterval);
            }
        }

        return resultIntervalList;
    }


}
