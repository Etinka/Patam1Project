package solver;

import java.util.ArrayList;

public class MySolver implements Solver {

    @Override
    public Solution solve(String level) {
        return new MySolution<>("123", new ArrayList<>());
    }
}
