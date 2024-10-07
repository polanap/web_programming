package utils;

public class Validator {

    public boolean validate(String x, String y, String R) throws Exception{
        try{
            double xPar = Double.parseDouble(x);
            // if (!(-3 <=xPar  && xPar <= 5 )){
            //     throw new Exception("invalid value of the x parameter");
            // }
        }catch(Exception e){
            throw new Exception("invalid value of the x parameter");
        }
        try{
            double yPar = Double.parseDouble(y);
            // if (!(-5 < yPar && yPar < 3)){
            //     throw new Exception("invalid value of the y parameter");
            // }
        }catch(Exception e){
            throw new Exception("invalid value of the y parameter");
        }
        try{
            double rPar = Double.parseDouble(R);
            // if (!( 1 <= rPar && rPar <= 5)){
            //     throw new Exception("invalid value of the R parameter");
            // }
        }catch(Exception e){
            throw new Exception("invalid value of the R parameter");
        }
        
        return true;
    }
}
