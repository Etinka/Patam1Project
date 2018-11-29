package server;


public abstract class MultiServerPriorityRunnable implements Comparable<MultiServerPriorityRunnable>, Runnable {
    private int puzzlePriority;

    @Override
    public int compareTo(MultiServerPriorityRunnable priorityRunnable) {
        return this.puzzlePriority - priorityRunnable.puzzlePriority;
    }

    MultiServerPriorityRunnable(int puzzlePriority) {
        this.puzzlePriority = puzzlePriority;
    }

    public int getPuzzlePriority() {
        return puzzlePriority;
    }
}