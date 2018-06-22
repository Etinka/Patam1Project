package algorithms;

import solver.Solution;

public interface Searcher<T extends Comparable<T>> {
    Solution search(Searchable<T> s);
}
