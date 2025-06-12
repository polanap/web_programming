package web.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.ObjectName;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;

@Data
@Named("tableRowBean")
@ApplicationScoped
public class TableRowBean implements Serializable {
    @Inject
    AreaCalculator areaCalculator;
    ObjectName areaCalculatorName;

    @PostConstruct
    public void init() {
        try {
            areaCalculatorName = new ObjectName("web.beans:type=AreaCalculator");
            System.out.println("------------------- START REGISTER BEAN -----------------------");
            ManagementFactory.getPlatformMBeanServer().registerMBean(areaCalculator, areaCalculatorName);
            System.out.println("------------------- FINISH REGISTER BEAN -----------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(areaCalculatorName);
        } catch (InstanceNotFoundException | MBeanRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    private double x = 1;
    private double y = 1;
    private double r = 1;
    private String resultData;
    private LocalDateTime currentTime;
    private double executionTime;

    public TableRow toEntity() {
        TableRow entity = new TableRow();
        entity.setX(x);
        entity.setY(y);
        entity.setR(r);
        entity.setResultData(resultData);
        entity.setCurrentTime(currentTime);
        entity.setExecutionTime(executionTime);
        return entity;
    }

    public void setR(double r) {
        areaCalculator.calculateArea(r);
        this.r = r;
    }
}
