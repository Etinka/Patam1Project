package algorithms;

import solver.Solution;

public interface Searcher<T> {
    Solution<T> search(Searchable<T> s);
}
