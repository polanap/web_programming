package web.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;
import web.beans.AreaCalculator;
import web.beans.PointTracker;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;

@Named("registrationListener")
@ApplicationScoped
public class MBeanRegistrationListener implements ServletContextListener {
    @Inject
    PointTracker pointTracker;
    ObjectName pointTrackerName = new ObjectName("web.beans:type=PointTracker");

    @Inject
    AreaCalculator areaCalculator;
    ObjectName areaCalculatorName = new ObjectName("web.beans:type=AreaCalculator");

    public MBeanRegistrationListener() throws MalformedObjectNameException {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ManagementFactory.getPlatformMBeanServer().registerMBean(pointTracker, pointTrackerName);
            ManagementFactory.getPlatformMBeanServer().registerMBean(areaCalculator, areaCalculatorName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(pointTrackerName);
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(areaCalculatorName);
        } catch (InstanceNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MBeanRegistrationException e) {
            throw new RuntimeException(e);
        }
    }
}
