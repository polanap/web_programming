package utils;

public class Validator {

    public boolean validate(String x, String y, String R) throws Exception{
        try{
            int xPar = Integer.parseInt(x);
            if (!(-3 <=xPar  && xPar <= 5 )){
                throw new Exception("invalid value of the x parameter");
            }
        }catch(Exception e){
            throw new Exception("invalid value of the x parameter");
        }
        try{
            float yPar = Float.parseFloat(y);
            if (!(-5 < yPar && yPar < 3)){
                throw new Exception("invalid value of the y parameter");
            }
        }catch(Exception e){
            throw new Exception("invalid value of the y parameter");
        }
        try{
            int rPar = Integer.parseInt(R);
            if (!( 1 <= rPar && rPar <= 5)){
                throw new Exception("invalid value of the R parameter");
            }
        }catch(Exception e){
            throw new Exception("invalid value of the R parameter");
        }
        
        return true;
    }
}
