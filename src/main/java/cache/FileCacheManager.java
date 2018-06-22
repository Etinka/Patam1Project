package cache;

import solver.Solution;

import java.io.*;
import java.util.HashMap;

public class FileCacheManager implements CacheManager {
    private final String fileName = "Solution.txt";
    private HashMap<String, Solution> localCache = new HashMap<>();

    public FileCacheManager() {
        loadLocalCacheFromFile();
    }

    @Override
    public void store(String level, Solution solution) {
        if (!localCache.containsKey(level)) {
            localCache.put(level, solution);
            //todo save to file
            writeLocalCacheToFile();
        }
    }

    @Override
    public Solution load(String level) {
        if (localCache.containsKey(level)) {
            return localCache.get(level);
        }
        return null;
    }

    private void writeLocalCacheToFile() {
        // write your answer here
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write object to file
            oos.writeObject(localCache);
            // closing resources
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLocalCacheFromFile() {
        try {
            File f = new File(fileName);
            if (f.isFile() && f.canRead()) {
                FileInputStream fos = new FileInputStream(fileName);
                ObjectInputStream inputStream = new ObjectInputStream(fos);
                localCache = (HashMap<String, Solution>) inputStream.readObject();
                fos.close();
                inputStream.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
