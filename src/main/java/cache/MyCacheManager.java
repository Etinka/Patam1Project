package cache;

import solver.Solution;

import java.util.HashMap;

public class MyCacheManager implements CacheManager {
    private HashMap<String, Solution> localCache = new HashMap<>();

    public MyCacheManager() {
        //todo load localCache from file
    }

    @Override
    public void store(String level, Solution solution) {
        if (!localCache.containsKey(level)) {
            localCache.put(level, solution);
            //todo save to file
        }
    }

    @Override
    public Solution load(String level) {
        if (localCache.containsKey(level)) {
            return localCache.get(level);
        }
        return null;
    }
}
