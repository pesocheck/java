package javalab1;

/**
 * Класс для хранения одной записи таблицы (ЛР2).
 * Хранит нижний предел, верхний предел, шаг и результат интегрирования.
 */
public class RecIntegral {
    
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

    /**
     * Вычисляет определённый интеграл функции f(x) = 1/ln(x)
     * методом трапеций на отрезке [lowerBound, upperBound] с заданным шагом.
     */
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

        // Проверка первой точки
        if (xPrev <= 0 || xPrev == 1) {
            result = "Ошибка (x)";
            return;
        }
        double fPrev = 1.0 / Math.log(xPrev);

        // Основной цикл метода трапеций
        for (double x = lowerBound + step; x <= upperBound; x += step) {
            if (x <= 0 || x == 1) {
                result = "Ошибка (x)";
                return;
            }

            double fCurr = 1.0 / Math.log(x);
            sum += (fPrev + fCurr) / 2 * step;  // площадь трапеции
            fPrev = fCurr;
        }

        if (xPrev + step < upperBound) {
            double xLast = upperBound;
            if (xLast <= 0 || xLast == 1) {
                result = "Ошибка (x)";
                return;
            }
            double fLast = 1.0 / Math.log(xLast);
            sum += (fPrev + fLast) / 2 * (upperBound - (xPrev + step - step));
        }

        result = String.format("%.6f", sum);
    }
   
 private void validateRange(double value) throws InvalidException {
    if (value < MIN_VALUE || value > MAX_VALUE) {
        throw new InvalidException(
            "Значение должно быть в диапазоне от " 
            + MIN_VALUE + " до " + MAX_VALUE 
            + ". Введено: " + value
        );
    }
}

    @Override
    public String toString() {
        return lowerBound + " " + upperBound + " " + step + " " + result;
    }
}   