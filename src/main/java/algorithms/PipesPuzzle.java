package algorithms;

import models.State;

import java.util.ArrayList;

public class PipesPuzzle implements Searchable<String> {
    private State<String> initialState;
    private int colNum;
    private int rowNum;

    public PipesPuzzle(State<String> initialState, int colNum, int rowNum) {
        this.initialState = initialState;
        this.colNum = colNum;
        this.rowNum = rowNum;
    }

    @Override
    public State<String> getInitialState() {
        return initialState;
    }


    @Override
    public boolean isGoal(State<String> state) {
        //Recursion
        boolean result = false;
        char[][] level = convertStringToChar(state.getState());
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (level[i][j] == 's') {

                    if (isValidIndex(i + 1, j)
                            && isMatch('s', level[i + 1][j], Direction.Down)) {
                        result = isGoal(i + 1, j, level, Direction.Down);
                        if (result) return true;
                    }
                    if (isValidIndex(i, j + 1)
                            && isMatch('s', level[i][j + 1], Direction.Right)) {
                        result = isGoal(i, j + 1, level, Direction.Right);
                        if (result) return true;
                    }
                    if (isValidIndex(i - 1, j)
                            && isMatch('s', level[i - 1][j], Direction.Up)) {
                        result = isGoal(i - 1, j, level, Direction.Up);
                        if (result) return true;
                    }
                    if (isValidIndex(i, j - 1)
                            && isMatch('s', level[i][j - 1], Direction.Left)) {
                        result = isGoal(i, j - 1, level, Direction.Left);
                        if (result) return true;
                    }
                }
            }
        }
        return false;
    }


    private boolean isGoal(int row, int col, char[][] level, Direction fromDirection) {
        if (level[row][col] == 'g') {
            return true;
        } else if (!isValidIndex(row, col)) {
            return false;
        } else if (isValidIndex(row + 1, col)
                && fromDirection != Direction.Up
                && isMatch(level[row][col], level[row + 1][col], Direction.Down)) {
            return isGoal(row + 1, col, level, Direction.Down);
        } else if (isValidIndex(row, col + 1)
                && fromDirection != Direction.Left
                && isMatch(level[row][col], level[row][col + 1], Direction.Right)) {
            return isGoal(row, col + 1, level, Direction.Right);
        } else if (isValidIndex(row - 1, col)
                && fromDirection != Direction.Down
                && isMatch(level[row][col], level[row - 1][col], Direction.Up)) {
            return isGoal(row - 1, col, level, Direction.Up);
        } else if (isValidIndex(row, col - 1)
                && fromDirection != Direction.Right
                && isMatch(level[row][col], level[row][col - 1], Direction.Left)) {
            return isGoal(row, col - 1, level, Direction.Left);
        }
        return false;
    }

    private boolean isMatch(char from, char to, Direction direction) {
        switch (direction) {
            case Up:
                switch (from) {
                    case '|':
                    case 'L':
                    case 'J':
                    case 's':
                        return to == '|' || to == 'F' || to == '7' || to == 'g';
                    default:
                        return false;
                }
            case Down:
                switch (from) {
                    case '|':
                    case '7':
                    case 'F':
                    case 's':
                        return to == 'L' || to == 'J' || to == '|' || to == 'g';
                    default:
                        return false;
                }
            case Left:
                switch (from) {
                    case '7':
                    case '-':
                    case 'J':
                    case 's':
                        return to == 'L' || to == '-' || to == 'F' || to == 'g';
                    default:
                        return false;
                }
            case Right:
                switch (from) {
                    case '-':
                    case 'L':
                    case 'F':
                    case 's':
                        return to == '-' || to == 'J' || to == '7' || to == 'g';
                    default:
                        return false;
                }
        }

        return false;
    }

    private boolean isValidIndex(int row, int col) {
        return row >= 0 && row < rowNum && col >= 0 && col < colNum;
    }

    private char[][] convertStringToChar(String levelString) {
        char[][] level = new char[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            level[i] = new char[colNum];
            level[i] = levelString.substring(i * colNum, (i * colNum) + colNum).toCharArray();
        }
        return level;
    }

    @Override
    public ArrayList<State<String>> getAllPossibleStates(State<String> s) {
        ArrayList<State<String>> possibleStates = new ArrayList<>();

        for (int i = 1; i < initialState.getState().length() - 1; i++) {
            StringBuilder newState = new StringBuilder(s.getState());
            newState.setCharAt(i, getNextChar(newState.charAt(i)));
            possibleStates.add(new State<>(newState.toString()));
        }
        return possibleStates;
    }

    private char getNextChar(char c) {
        switch (c) {
            case '|':
                return '-';
            case '-':
                return '|';
            case 'L':
                return 'F';
            case 'F':
                return '7';
            case '7':
                return 'J';
            case 'J':
                return 'L';
            default:
                return '-';
        }
    }

    enum Direction {
        Up, Down, Left, Right
    }
}
