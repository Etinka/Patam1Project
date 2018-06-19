package solver;

public interface Solution<T> {
    T getFinalBoard();
    void printFinalBoard();
    void printSteps();
}
