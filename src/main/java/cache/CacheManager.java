package cache;

import solver.Solution;

public interface CacheManager {
    void store(String level, Solution solution);
    Solution load(String level);
}
//map of solutions
//saving according to level hash -> solution
