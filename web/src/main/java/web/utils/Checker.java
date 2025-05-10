package web.utils;

public class Checker {
    
    public boolean check(double x, double y, double R){
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
}
