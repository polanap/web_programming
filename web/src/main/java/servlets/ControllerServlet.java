package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;

import com.fasterxml.jackson.databind.*;


public class ControllerServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.mark(0);
            reader.reset();
        }
        String jsonData = sb.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        Request params = objectMapper.readValue(jsonData, Request.class);  
        
        String x = params.x();
        String y = params.y();
        String R = params.R();
        request.setAttribute("x", x);
        request.setAttribute("y", y);
        request.setAttribute("R", R);
        
        if (x != null && y != null && R != null) {
            getServletContext().getRequestDispatcher("/model").forward(request, response);
        } else {
            String message = """
                    {
                        "error":"passed param",
                        "x":"%s",
                        "y":"%s",
                        "R":"%s"

                    }
                    """.formatted(x, y, R);
            PrintWriter writer = response.getWriter();
            writer.println(message);
            response.setStatus(400);
            // response.setContentLength(message.length());
            response.setContentType("json");
            writer.close(); 
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        getServletContext().getRequestDispatcher("/model").forward(request, response);

    }
}
