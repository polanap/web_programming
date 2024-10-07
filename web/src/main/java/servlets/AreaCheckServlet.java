package servlets;

import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import table.TableHistoryBean;
import table.TableRow;
import utils.Checker;
import utils.Request;
import utils.Validator;
import java.io.BufferedReader;

import java.io.IOException;
import java.time.LocalDateTime;

public class AreaCheckServlet extends HttpServlet{
    private Validator validator;
    private Checker checker;

    @Override
    public void init() throws ServletException {
        super.init();
        validator = new Validator();
        checker = new Checker();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String message = "";
        try{
           
            String x = request.getAttribute("x").toString();
            String y = request.getAttribute("y").toString();
            String R = request.getAttribute("R").toString();

            if (validator.validate(x, y, R)){
                double xPar = Double.parseDouble(x);
                double yPar = Double.parseDouble(y);
                double rPar = Double.parseDouble(R);

                long startTime = System.nanoTime();
                String result = checker.check(xPar, yPar, rPar) ? "inner" : "outer";
                var dateTime = LocalDateTime.now();
                long endTime = System.nanoTime();
                float duration = (endTime - startTime);

                var row = new TableRow();
                row.setX(xPar);
                row.setY(yPar);
                row.setR(rPar);
                row.setResultData(result);
                row.setCurrentTime(dateTime.toString());
                row.setExecutionTime(duration/1000000);

                var session = request.getSession();
                TableHistoryBean history = (TableHistoryBean)session.getAttribute("history");
                var newHistory = history.getHistory();
                newHistory.add(row);
                history.setHistory(newHistory);
                

                message =  """
                {
                    "x":"%s",
                    "y":"%s",
                    "R":"%s",
                    "resultData":"%s",
                    "currentTime":"%s",
                    "executionTime":"%s"
                }
                """.formatted(x, y, R, result, dateTime.toString(), String.format("%.10f", duration/1000000));
                response.setStatus(200);
            }else{
                message = """
                {
                    "error" : "invalid data"
                }
                """;
            response.setStatus(400);
            }
        }catch(Exception e){
            message = """
                {
                    "error" : "%s | %s"
                }
                """.formatted(e, e.getMessage());
            response.setStatus(400);
        }
        PrintWriter writer = response.getWriter();
        writer.println(message);
        // response.setContentLength(message.length());
        response.setContentType("application/json");
        writer.close();     
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var session = request.getSession();
        TableHistoryBean history = (TableHistoryBean)session.getAttribute("history");
        if (history == null){
            history = new TableHistoryBean();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(history);
        PrintWriter writer = response.getWriter();
        writer.println(message);
        response.setContentLength(message.length());
        response.setContentType("application/json");
        writer.close();   


    }
    
}
