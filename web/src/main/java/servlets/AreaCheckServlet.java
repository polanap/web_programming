package servlets;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import table.TableHistoryBean;
import utils.Checker;
import utils.Validator;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/model")
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

        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String R = request.getParameter("R");
        String message = "";
        try{
            if (validator.validate(x, y, R)){
                int xPar = Integer.parseInt(x);
                float yPar = Float.parseFloat(y);
                int rPar = Integer.parseInt(R);
                long startTime = System.nanoTime();
                String result = checker.check(xPar, yPar, rPar) ? "внутри" : "снаружи";
                var dateTime = LocalDateTime.now();
                long endTime = System.nanoTime();
                float duration = (endTime - startTime);
                message =  """
                {
                    "x":"%s",
                    "y":"%s",
                    "R":"%s",
                    "result":"%s",
                    "currentTime":"%s",
                    "executionTime":"%s"
                }
                """.formatted(x, y, R, result, dateTime, String.format("%.10f", duration/1000000));
                response.setStatus(200);
            }
        }catch(Exception e){
            message = """
                {
                    "error" : "%s"
                }
                """.formatted(e.getMessage());
            response.setStatus(400);
        }
        PrintWriter writer = response.getWriter();
        writer.println(message);
        response.setContentLength(message.length());
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
