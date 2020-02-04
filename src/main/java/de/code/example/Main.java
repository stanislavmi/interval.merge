package de.code.example;

import de.code.example.object.Interval;

import java.util.*;

/**
 * Java Main class
 * run both solution with predefined or random input
 *
 * @author Stanislav Michel
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("java -jar interval.merge-1.0-SNAPSHOT-jar-with-dependencies.jar <number?>");
        System.out.println("Empty argument - default example otherwise input number of random intervals");
        if (args.length == 0) {
            //Default example
            List intervalList = new ArrayList<Interval>();
            intervalList.add(new Interval(25, 30));
            intervalList.add(new Interval(2, 19));
            intervalList.add(new Interval(14, 23));
            intervalList.add(new Interval(4, 8));
            merge(intervalList);
        } else {
            try {
                int numberOfIntervals = Integer.parseInt(args[0]);
                if (numberOfIntervals > 0)
                    merge(getRandomIntervalList(numberOfIntervals));
                else System.out.println("Allow only positive number");
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Input is not number!");
            }
        }

    }

    /**
     * Check merge solution of mergeList and mergeTreemap
     *
     * @param intervalList
     */
    public static void merge(List intervalList) {
        long start = System.currentTimeMillis();
        printList(intervalList, "Input " + intervalList + "\n");
        IntervalMerger intervalMerger = new IntervalMerger();
        List<Interval> resultList = intervalMerger.mergeList(intervalList);
        printList(resultList, "Result through List " + resultList);
        System.out.println("Duration through List solution " + (System.currentTimeMillis() - start) + " Millis\n");
        start = System.currentTimeMillis();
        resultList = intervalMerger.mergeTreeMap(intervalList);
        printList(resultList, "Result through TreeMap " + resultList);
        System.out.println("Duration through TreeMap solution " + (System.currentTimeMillis() - start) + " Millis\n");
    }

    /**
     * Print lists to output if max size 100
     *
     * @param list         - list to output
     * @param outputString - other information output for list
     */
    public static void printList(List list, String outputString) {
        if (list.size() < 100)
            System.out.println(outputString);
    }

    /**
     * Get random generated intervals
     *
     * @param numberOfIntervals - number of intervals
     * @return List of random generated intervals
     */
    public static List<Interval> getRandomIntervalList(int numberOfIntervals) {
        Map<Integer, Integer> intervalMap = new TreeMap<>();
        //Create TreeMap with unique key
        for (int i = 0; i < numberOfIntervals; i++) {
            int randomPoint = (int) (Math.random() * numberOfIntervals);
            intervalMap.put(randomPoint, (int) (randomPoint + (Math.random() * 10) + 1));
        }
        //Write from TreeMap in List, ordered by TreeMap key
        List<Interval> intervalList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : intervalMap.entrySet()) {
            intervalList.add(new Interval(entry.getKey(), entry.getValue()));
        }
        // Shuffle List entries
        Collections.shuffle(intervalList);
        return intervalList;
    }


}
