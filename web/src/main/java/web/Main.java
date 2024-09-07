package web;
import java.nio.charset.StandardCharsets;

import com.fastcgi.FCGIInterface;
import java.time.LocalDateTime;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        var fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            Properties params = FCGIInterface.request.params;
            int x = Integer.parseInt(params.getProperty("x"));
            float y = Float.parseFloat(params.getProperty("y"));
            int R = Integer.parseInt(params.getProperty("R"));
            var content = submit(x, y, R);
            var httpResponse = """
            HTTP/1.1 200 OK
            Content-Type: application/json
            Content-Length: %d
            %s
            """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);
            System.out.println(httpResponse);
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
            "x":%s,
            "y":%s,
            "R":%s,
            "result":%s,
            "currentTime":%s,
            "executionTime":%s
        }
        """.formatted(x, y, R, result, dateTime, duration);

    }

}
