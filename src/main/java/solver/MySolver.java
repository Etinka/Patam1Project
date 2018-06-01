package solver;

public class MySolver implements Solver {

    @Override
    public Solution solve(String level) {
        return new MySolution("123", 1, 3);
    }
}
