package de.code.example.object;

import java.util.Objects;

/**
 * Interval object
 *
 * @author Stanislav Michel
 */
public class Interval {

    /**
     * Start point of interval
     */
    private int startPoint;

    /**
     * End point of interval
     */
    private int endPoint;

    /**
     * @param startPoint to set
     * @param endPoint to set
     */
    public Interval(int startPoint, int endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    /**
     * @return current startPoint
     */
    public int getStartPoint() {
        return startPoint;
    }

    /**
     * @param startPoint to set
     */
    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * @return current endPoint
     */
    public int getEndPoint() {
        return endPoint;
    }

    /**
     * @param endPoint to set
     */
    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return "[" + startPoint +
                ", " + endPoint +
                ']';
    }

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
