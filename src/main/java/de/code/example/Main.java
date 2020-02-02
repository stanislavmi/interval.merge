package de.code.example;

import de.code.example.object.Interval;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("java -jar interval.merge-1.0-SNAPSHOT-jar-with-dependencies.jar <number?>");
        System.out.println("Empty argument - default example");
        System.out.println("Number argument - number of random intervals");
        if (args.length == 0) {
            List intervalList = new ArrayList<Interval>();
            intervalList.add(new Interval(25, 30));
            intervalList.add(new Interval(2, 19));
            intervalList.add(new Interval(14, 23));
            intervalList.add(new Interval(4, 8));
            runTest(intervalList);
        } else {
            try {
                int numberOfIntervals = Integer.parseInt(args[0]);
                runTest(getIntervalList(numberOfIntervals));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Input is not number!");
            }
        }

    }

    public static void runTest(List intervalList) {
        long start = System.currentTimeMillis();
        System.out.println("Input " + intervalList + "\n");
        IntervalMerger intervalMerger = new IntervalMerger();
        System.out.println("Durch List" + intervalMerger.mergeList(intervalList));
        intervalMerger.mergeList(intervalList);
        System.out.println(System.currentTimeMillis() - start + " Millis\n");

        start = System.currentTimeMillis();
        System.out.println("Durch TreeMap" + intervalMerger.mergeTreeMap(intervalList));
        intervalMerger.mergeTreeMap(intervalList);
        System.out.println(System.currentTimeMillis() - start + " Millis\n");
    }

    // Zufällige Intervall Generierung
    public static List<Interval> getIntervalList(int numberOfIntervals) {
        Map<Integer, Integer> intervalMap = new TreeMap<>();
        for (int i = 0; i < numberOfIntervals; i++) {
            int randomPoint = (int) (Math.random() * numberOfIntervals);
            intervalMap.put(randomPoint, (int) (randomPoint + (Math.random() * 10) + 1));
        }
        List<Interval> intervalList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : intervalMap.entrySet()) {
            intervalList.add(new Interval(entry.getKey(), entry.getValue()));
        }

        return intervalList;
    }

}
