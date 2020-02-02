package de.code.example.object;

import java.util.Objects;

//Intervall Object mit start und end Punkt
public class Interval {
    private int startPoint;
    private int endPoint;

    public Interval(int startPoint, int endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public Interval setStartPoint(int startPoint) {
        this.startPoint = startPoint;
        return this;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public Interval setEndPoint(int endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    @Override
    public String toString() {
        return "[" + startPoint +
                ", " + endPoint +
                ']';
    }

    //Überschriebene default equals und hashCode Klasse, damit man mit diesen Objekten arbeiten kann
    //z.B. bei Collections Methoden, wo man die Methoden mit Object Argumenten hat
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return startPoint == interval.startPoint &&
                endPoint == interval.endPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint);
    }
}
