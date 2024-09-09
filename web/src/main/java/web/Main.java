package web;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import com.fastcgi.FCGIInterface;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        var fcgiInterface = new FCGIInterface();
        var stderr = System.err;
        String HERNIYA = "123";
            while (fcgiInterface.FCGIaccept() >= 0) {
                try{
                Properties params = FCGIInterface.request.params;
                var queryString = params.getProperty("QUERY_STRING");
                Pattern xPattern = Pattern.compile("[&^]x=(?<x>[^&]*)");
                Pattern yPattern = Pattern.compile("[&^]y=(?<y>[^&]*)");
                Pattern rPattern = Pattern.compile("[&^]R=(?<R>[^&]*)");

                int x = 0;
                float y = 0;
                int R = 0;

                Matcher xMatcher = xPattern.matcher(queryString);
                if (xMatcher.find()) {
                    x = Integer.parseInt(xMatcher.group("x"));
                }
                Matcher yMatcher = yPattern.matcher(queryString);
                if (yMatcher.find()) {
                    y = Float.parseFloat(yMatcher.group("y"));
                }
                Matcher rMatcher = rPattern.matcher(queryString);
                if (rMatcher.find()) {
                    R = Integer.parseInt(rMatcher.group("R"));
                }

                // var queryParams = queryString.split("&");
                // int x = Integer.parseInt(queryParams[0].replaceAll(".=", ""));
                // float y = Float.parseFloat(queryParams[1].replaceAll(".=", ""));
                // int R = Integer.parseInt(queryParams[2].replaceAll(".=", ""));
                
                var content = submit(x, y, R);
                var httpResponse = """
                HTTP/1.1 200 OK
                Content-Type: application/json
                Content-Length: %d

                %s
                """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);
                System.out.println(httpResponse);
            }catch (Exception e){
                System.setErr(stderr);
                System.err.println(e.getMessage());
                System.err.println(HERNIYA);
                e.printStackTrace();
            }
            }


        
        
    }

    private static boolean check(int x, float y, int R){
        if (x < 0 && y > 0 && x*x+y*y <= R*R){
            return true;
        }
        else if (-R/2<=x && x<= 0 && -R<=y && y<=0){
            return true;   
        }
        else if (x>=0 && y<=0 && y>=-R/2+x/2){
            return true;
        }
        else{
            return false;
        }
    }
    private static String submit(int x, float y, int R){
        long startTime = System.nanoTime();
        String result = check(x, y, R) ? "внутри" : "снаружи";
        var dateTime = LocalDateTime.now();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        return """
        {
            "x":"%s",
            "y":"%s",
            "R":"%s",
            "result":"%s",
            "currentTime":"%s",
            "executionTime":"%s"
        }
        """.formatted(x, y, R, result, dateTime, duration);

    }

}
