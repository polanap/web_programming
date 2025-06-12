package web.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;

@Named("areaCalculator")
@ApplicationScoped
@NoArgsConstructor
public class AreaCalculator implements AreaCalculatorMBean {
    private double S;

    @Override
    public void setS(double S) {
        this.S = S;
    }

    @Override
    public double getS() {
        return S;
    }

    @Override
    public double calculateArea(Double radius) {
        S = (Math.PI + 3)*radius*radius/4;
        return S;
    }
}