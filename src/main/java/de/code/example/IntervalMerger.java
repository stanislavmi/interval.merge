package de.code.example;

import de.code.example.object.Interval;

import java.util.*;

// Klasse für merge von Listen oder TreeMap
public class IntervalMerger {

    // Merge Methode, die Result Intervalle als eine Liste von Interval Objekten zurückgibt
    public List<Interval> mergeList(List<Interval> intervalList) {

        // Ergebnis Liste enthält am Ende alle Intervalle, die gemerged sind
        List<Interval> resultInterval = new ArrayList<>();

        // Check, ob die List ein oder gar keine Elemente enthält und gibt das raus, falls es die Bedingung stimmt
        if (intervalList.size() <= 1)
            return intervalList;
        // Sortierung von Elementen in Liste nach startPoint
        // Collections.sort macht merge sort mit Komplexität O(n*(log(n)))
        Collections.sort(intervalList, Comparator.comparingInt(o -> o.getStartPoint()));
        // Erstes Element in firstInterval speichern, firstInterval wird auch als eine Temp Variable benutzt
        Interval firstInterval = new Interval(intervalList.get(0).getStartPoint(), intervalList.get(0).getEndPoint());
        // Dann alle Elemente durchgehen, ab Index 1, denn mit Index 0 ist schon in firstInterval geschrieben
        for (int i = 1; i < intervalList.size(); i++) {
            // Prüfen, ob im ersten Intervall letzter Punkt kleiner als bei nächstem Interval der erste Punkt
            if (firstInterval.getEndPoint() < intervalList.get(i).getStartPoint()) {
                //wenn ja, dann schreiben das aktuelle Intervall in Ergebnis Liste
                resultInterval.add(intervalList.get(i));
                firstInterval = intervalList.get(i);
            }
            // anderfalls, wenn im erstem Intervall der letzte Punkt kleiner als der letzte Punkt von aktuellem Intervall
            else if (firstInterval.getEndPoint() < intervalList.get(i).getEndPoint()) {
                // Löschen firstInterval aus Ergebnis Liste
                resultInterval.remove(firstInterval);
                // Setzen von letztem Punkt aus aktuellen Interval in firstInterval
                firstInterval.setEndPoint(intervalList.get(i).getEndPoint());
                // und schreiben in Ergebnis Liste
                resultInterval.add(firstInterval);
            }
        }

        // Ergebnis Ausgabe
        return resultInterval;
    }

    // TreeMap<V,U> - Ordered Red-Black tree
    // TreeMap<Integer,Integer>: key ist startPoint, value ist endPoint
    // Einfügen Komplexität O(log(n)) - schneller als Collections.sort
    public List<Interval> mergeTreeMap(List<Interval> intervalList) {
        // Ergebnis Liste enthält am Ende alle Intervalle, die gemerged sind
        List<Interval> resultInterval = new ArrayList<>();

        TreeMap<Integer, Integer> intervalMap = new TreeMap<>();
        intervalList.forEach(a -> intervalMap.put(a.getStartPoint(), a.getEndPoint()));

        // Check, ob TreeMap ein oder gar keine Elemente enthält und gibt das raus, falls es die Bedingung stimmt
        if (intervalMap.size() <= 1)
            return (intervalMap.size() == 0 ? resultInterval : Collections.singletonList(new Interval(intervalMap.firstKey(), intervalMap.firstEntry().getValue())));
        // Erstes Element in firstInterval speichern, firstInterval wird auch als eine Temp Variable benutzt
        Map.Entry<Integer, Integer> firstEntry = intervalMap.pollFirstEntry();
        Interval firstInterval = new Interval(firstEntry.getKey(), firstEntry.getValue());
        //Iterieren von TreeMap
        for (Map.Entry<Integer, Integer> entry : intervalMap.entrySet()) {
            // Prüfen, ob im ersten Intervall letzter Punkt kleiner als bei nächstem Interval der erste Punkt
            if (firstInterval.getEndPoint() < entry.getKey()) {
                //wenn ja, dann schreiben das aktuelle Intervall in Ergebnis Liste
                resultInterval.add(new Interval(entry.getKey(), entry.getValue()));
                firstInterval = new Interval(entry.getKey(), entry.getValue());
            }
            // anderfalls, wenn im erstem Intervall der letzte Punkt kleiner als der letzte Punkt von aktuellem Intervall
            else if (firstInterval.getEndPoint() < entry.getValue()) {
                // Löschen firstInterval aus Ergebnis Liste
                resultInterval.remove(firstInterval);
                // Setzen von letztem Punkt aus aktuellen Interval in firstInterval
                firstInterval.setEndPoint(entry.getValue());
                // und schreiben in Ergebnis Liste
                resultInterval.add(firstInterval);
            }
        }
        return resultInterval;
    }
}
