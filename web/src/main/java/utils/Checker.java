package utils;

public class Checker {
    
    public boolean check(int x, float y, int R){
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
