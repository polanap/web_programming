package web.beans;

public interface PointTrackerMBean {
    void addPoint(double x, double y, boolean isPointInArea);
    int getTotalPoints();
    int getMissedPoints();
    void reset();
}
