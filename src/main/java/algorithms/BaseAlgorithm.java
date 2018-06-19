package algorithms;

abstract class BaseAlgorithm<T extends Comparable<T>> implements Searcher<T> {
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
        System.out.println("startTime: " + startTime);
        System.out.println("endTime: " + endTime);
        System.out.println("Total time: " + getTotalTime());
    }


}
