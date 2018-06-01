package cache;

import solver.Solution;

public interface CacheManager {
    void store(int level, Solution solution);
    Solution load(int level);
}
