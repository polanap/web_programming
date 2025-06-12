package web.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Named("tableRowBean")
@ApplicationScoped
public class TableRowBean implements Serializable {
    @Inject
    AreaCalculator areaCalculator;

    private double x = 1;
    private double y = 1;
    private double r = 1;
    private String resultData;
    private LocalDateTime currentTime;
    private double executionTime;

    public TableRow toEntity(){
        TableRow entity = new TableRow();
        entity.setX(x);
        entity.setY(y);
        entity.setR(r);
        entity.setResultData(resultData);
        entity.setCurrentTime(currentTime);
        entity.setExecutionTime(executionTime);
        return entity;
    }

    public void setR(double r){
        areaCalculator.calculateArea(r);
        this.r = r;
    }
}
