package web.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;


@Named("pointTracker")
@ApplicationScoped
@NoArgsConstructor
public class PointTracker extends NotificationBroadcasterSupport implements PointTrackerMBean {
    private int totalPoints = 0;
    private int missedPoints = 0;
    private int consecutiveMisses = 0;
    private static final int MAX_CONSECUTIVE_MISSES = 4;
    private long notificationSequence = 1;

    // Метод для добавления точки
    @Override
    public void addPoint(double x, double y, boolean isPointInArea) {
        totalPoints++;
        if (!isPointInArea) {
            missedPoints++;
            consecutiveMisses++;
            if (consecutiveMisses >= MAX_CONSECUTIVE_MISSES) {
                sendNotification(new Notification("miss.notification", this, notificationSequence++, System.currentTimeMillis(), "4 consecutive misses!"));
                consecutiveMisses = 0; // Сбросить счетчик после уведомления
            }
        } else {
            consecutiveMisses = 0; // Сбросить счетчик, если точка попала в область
        }
    }

    @Override
    public int getTotalPoints() {
        return totalPoints;
    }

    @Override
    public int getMissedPoints() {
        return missedPoints;
    }

    @Override
    public void reset() {
        totalPoints = 0;
        missedPoints = 0;
        consecutiveMisses = 0;
    }
}