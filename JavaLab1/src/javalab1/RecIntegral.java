package javalab1;

import java.io.Serializable;

public class RecIntegral implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final double MIN_VALUE = 0.000001;
    private static final double MAX_VALUE = 1000000;

    private double lowerBound;
    private double upperBound;
    private double step;
    private String result;

    public RecIntegral(double lowerBound, double upperBound, double step) throws InvalidException {

        validateRange(lowerBound);
        validateRange(upperBound);
        validateRange(step);

        if (upperBound < lowerBound) {
            throw new InvalidException(
                "Верхний предел не может быть меньше нижнего!",
                upperBound
            );
        }

        if (step <= 0) {
            throw new InvalidException(
                "Шаг должен быть положительным!",
                step
            );
        }

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.step = step;
        this.result = "";
    }

    public double getLowerBound() { return lowerBound; }
    public void setLowerBound(double lowerBound) { this.lowerBound = lowerBound; }

    public double getUpperBound() { return upperBound; }
    public void setUpperBound(double upperBound) { this.upperBound = upperBound; }

    public double getStep() { return step; }
    public void setStep(double step) { this.step = step; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public void calculate() {

        if (step <= 0) {
            result = "Ошибка (шаг)";
            return;
        }

        if (lowerBound >= upperBound) {
            result = "Ошибка (границы)";
            return;
        }

        double sum = 0;
        double xPrev = lowerBound;

        if (xPrev <= 0 || xPrev == 1) {
            result = "Ошибка (x)";
            return;
        }

        double fPrev = 1.0 / Math.log(xPrev);

        for (double x = lowerBound + step; x <= upperBound; x += step) {

            if (x <= 0 || x == 1) {
                result = "Ошибка (x)";
                return;
            }

            double fCurr = 1.0 / Math.log(x);
            sum += (fPrev + fCurr) / 2 * step;

            fPrev = fCurr;
            xPrev = x;
        }

        if (xPrev < upperBound) {
            double xLast = upperBound;

            if (xLast <= 0 || xLast == 1) {
                result = "Ошибка (x)";
                return;
            }

            double fLast = 1.0 / Math.log(xLast);
            sum += (fPrev + fLast) / 2 * (upperBound - xPrev);
        }

        result = String.format("%.6f", sum).replace(",", ".");
    }

    private void validateRange(double value) throws InvalidException {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new InvalidException(
                "Значение должно быть в диапазоне от "
                + MIN_VALUE + " до " + MAX_VALUE
                + ". Введено: " + value,
                value
            );
        }
    }

    @Override
    public String toString() {
        String res = (result == null || result.isEmpty()) ? "0" : result;
        return lowerBound + " " + upperBound + " " + step + " " + res;
    }
}