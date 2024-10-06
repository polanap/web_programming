package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ControllerServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String R = request.getParameter("R");
        
        if (x != null && y != null && R != null) {
            getServletContext().getRequestDispatcher("/model").forward(request, response);
        } else {
            String message = """
                    {
                        "error":"passed param"
                    }
                    """;
            PrintWriter writer = response.getWriter();
            writer.println(message);
            response.setStatus(400);
            writer.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        getServletContext().getRequestDispatcher("/model").forward(request, response);

    }
}
