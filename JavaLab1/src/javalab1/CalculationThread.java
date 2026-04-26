package javalab1;

public class CalculationThread implements Runnable {
    private final double start;
    private final double end;
    private final double step;
    private final int threadIndex;
    private final double[] partialResults;

    public CalculationThread(double start, double end, double step, int threadIndex, double[] partialResults) {
        this.start = start;
        this.end = end;
        this.step = step;
        this.threadIndex = threadIndex;
        this.partialResults = partialResults;
    }

    @Override
    public void run() {
        double sum = 0;
        double xPrev = start;
        
        if (xPrev <= 0 || xPrev == 1) return;

        double fPrev = 1.0 / Math.log(xPrev);

        for (double x = start + step; x <= end; x += step) {
            if (x <= 0 || x == 1) return;
            double fCurr = 1.0 / Math.log(x);
            sum += (fPrev + fCurr) / 2 * step;
            fPrev = fCurr;
            xPrev = x;
        }

        if (xPrev < end) {
            double fLast = 1.0 / Math.log(end);
            sum += (fPrev + fLast) / 2 * (end - xPrev);
        }

        partialResults[threadIndex] = sum;
    }
}