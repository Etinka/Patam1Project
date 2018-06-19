package algorithms;

import solver.Solution;

class BaseAlgorithm<T> {
    private long startTime;
    private long endTime;

    private long getTotalTime() {
        return endTime - startTime;
    }

    void startSearch() {
        startTime = System.currentTimeMillis();
    }

    void endSearch() {
        endTime = System.currentTimeMillis();
        System.out.println("Total time: " + getTotalTime());
    }


}
