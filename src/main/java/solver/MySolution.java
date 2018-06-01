package solver;

public class MySolution implements Solution {
    private char[][] level;
    private int colNum;
    private int colRow;
    private String levelString;

    public MySolution(String levelString, int colRow, int colNum) {
        //Move to solution
        this.levelString = levelString;
        level = new char[colRow][colNum];
        for (int i = 0; i < colRow; i++) {
            level[i] = new char[colNum];
            level[i] = levelString.substring(i * colNum, (i * colNum) + colNum).toCharArray();
        }
        this.colNum = colNum;
        this.colRow = colRow;
    }

    public MySolution() {
        this("", 0, 0);
    }

    @Override
    public String getLevelString() {
        return levelString;
    }

    @Override
    public void print() {
        for (int i = 0; i < colRow; i++) {
            for (int j = 0; j < colNum; j++) {
                System.out.print(level[i][j]);
            }
            System.out.println();
        }
    }
}
