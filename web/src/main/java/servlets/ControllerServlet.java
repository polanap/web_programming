package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/controller")
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
}
