package web.beans;

import lombok.NoArgsConstructor;
import web.utils.Checker;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.time.LocalDateTime;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("table")
@NoArgsConstructor
@Data
@ApplicationScoped
public class TableHistoryBean implements Serializable {
    private TableRow newRow = new TableRow();
    private static Checker checker = new Checker();
    private TableRowDAO tableRowDAO = new TableRowDAO();
    private List<TableRow> history = tableRowDAO.getAttemptsList();
    
    public void addNewRow(){
        setData();
        history.add(newRow);
        tableRowDAO.addAttempt(newRow);
        double x = newRow.getX();
        double y = newRow.getY();
        double R = newRow.getR();
        newRow = new TableRow();
        newRow.setX(x);
        newRow.setY(y);
        newRow.setR(R);
    }
    
    public List<TableRow> getPartOfHistory(Double r){
        List<TableRow> filteredHistory = new ArrayList<>();
        Stream<TableRow> tableRowStream = history.stream();
        tableRowStream.filter(s->s.getR()==r).forEach(s-> filteredHistory.add(s));
        return filteredHistory;
    }

    public String getResultData(){
        double x = newRow.getX();
        double y = newRow.getY();
        double R = newRow.getR();
        return checker.check(x, y, R) ? "inner" : "outer";
    }
    public LocalDateTime getCurrentTime(){
        return LocalDateTime.now();
    }

    public void setData(){

        long startTime = System.nanoTime();
        String result = getResultData();
        long endTime = System.nanoTime();
        float duration = (endTime - startTime);

        newRow.setResultData(result);
        newRow.setCurrentTime(getCurrentTime());
        newRow.setExecutionTime(duration/1000000);
    }

}