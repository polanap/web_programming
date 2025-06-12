package web.beans;

public interface AreaCalculatorMBean {
    void setS(double S);
    double getS();
    double calculateArea(Double radius);
}