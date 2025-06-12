package web.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.utils.Checker;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.ObjectName;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Named("table")
@NoArgsConstructor
@Data
@ApplicationScoped
public class TableHistoryBean implements Serializable {
    private static Checker checker = new Checker();
    private TableRowDAO tableRowDAO = new TableRowDAO();
    private List<TableRow> history = tableRowDAO.getAttemptsList();
    ObjectName pointTrackerName;

    @Inject
    private TableRowBean newRow;

    @Inject
    PointTracker pointTracker;

    @PostConstruct
    public void init() {
        try {
            pointTrackerName = new ObjectName("web.beans:type=PointTracker");
            System.out.println("------------------- START REGISTER BEAN 2 -----------------------");
            ManagementFactory.getPlatformMBeanServer().registerMBean(pointTracker, pointTrackerName);
            System.out.println("------------------- FINISH REGISTER BEAN 2 -----------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(pointTrackerName);
        } catch (InstanceNotFoundException | MBeanRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewRow() {
        setData();
        history.add(newRow.toEntity());
        tableRowDAO.addAttempt(newRow.toEntity());
        double x = newRow.getX();
        double y = newRow.getY();
        double R = newRow.getR();
//        newRow = new TableRowBean();
        newRow.setX(x);
        newRow.setY(y);
        newRow.setR(R);

        pointTracker.addPoint(x, y, checker.check(x, y, R));
    }

    public List<TableRow> getPartOfHistory(Double r) {
        List<TableRow> filteredHistory = new ArrayList<>();
        Stream<TableRow> tableRowStream = history.stream();
        tableRowStream.filter(s -> s.getR() == r).forEach(s -> filteredHistory.add(s));
        return filteredHistory;
    }

    public String getResultData() {
        double x = newRow.getX();
        double y = newRow.getY();
        double R = newRow.getR();
        return checker.check(x, y, R) ? "inner" : "outer";
    }

    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    public void setData() {

        long startTime = System.nanoTime();
        String result = getResultData();
        long endTime = System.nanoTime();
        float duration = (endTime - startTime);

        newRow.setResultData(result);
        newRow.setCurrentTime(getCurrentTime());
        newRow.setExecutionTime(duration / 1000000);
    }

}