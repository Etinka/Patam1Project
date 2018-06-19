package algorithms;

import solver.Solution;

public interface Searcher<T extends Comparable<T>> {
    Solution<T> search(Searchable<T> s);
}
