package algorithms;

import models.PuzzleState;
import models.State;

import java.util.ArrayList;

public class PipesPuzzle implements Searchable<char[][]> {
    private PuzzleState initialState;
    private int colNum, rowNum, startColNum, startRowNum, goalColNum, goalRowNum;

    public PipesPuzzle(PuzzleState initialState, int rowNum, int colNum) {
        this.initialState = initialState;
        this.rowNum = rowNum;
        this.colNum = colNum;
        findStartAndGoal();
    }

    private void findStartAndGoal() {
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (initialState.getState()[i][j] == 's') {
                    startRowNum = i;
                    startColNum = j;
                } else if (initialState.getState()[i][j] == 'g') {
                    goalColNum = i;
                    goalRowNum = j;
                }
            }
        }
    }

    @Override
    public State<char[][]> getInitialState() {
        return initialState;
    }


    @Override
    public boolean isGoal(State<char[][]> state) {
        //Recursion
        boolean result = false;
        char[][] level = state.getState();//convertStringToChar(state.getState());
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
    public ArrayList<State<char[][]>> getAllPossibleStates(State<char[][]> s) {
        ArrayList<State<char[][]>> possibleStates = new ArrayList<>();

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                char[][] newState = new char[rowNum][];

                for (int l = 0; l < rowNum; l++) {
                    newState[l] = s.getState()[l].clone();
                }

                newState[i][j] = getNextChar(newState[i][j]);
                possibleStates.add(new PuzzleState(newState, rowNum));
            }
        }

//        System.out.println("getAllPossibleStates");
        return possibleStates;
    }

    @Override
    public int grade(State state) {
        return heuristicGrade(state);
    }

    public int heuristicGrade(State<char[][]> state) {
        return manhattanGrade(state.getState(), startRowNum, startColNum, Direction.Start);
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
            case 's':
                return 's';
            case 'g':
                return 'g';
            default:
                return ' ';
        }
    }

    private int manhattanGrade(char[][] board, int row, int col, Direction c) {
        int moveRight = col + 1;
        int moveLeft = col - 1;
        int moveUp = row - 1;
        int moveDown = row + 1;
        if (isValidIndex(row, col))
            return 0;
        if (board[row][col] == 'g')
            return 1;
        if (board[row][col] == ' ')
            return 0;
        if (board[row][col] == 'g' && c != Direction.Start)
            return 0;
        switch (c) {
            case Start:
                return Math.max(Math.max(Math.max(manhattanGrade(board, row, moveRight, Direction.Right), manhattanGrade(board, row, moveLeft, Direction.Left))
                        , manhattanGrade(board, moveUp, col, Direction.Up))
                        , manhattanGrade(board, moveDown, col, Direction.Down)) + 1;

            case Up:
                if (board[row][col] == '|')
                    return manhattanGrade(board, moveUp, col, Direction.Up) + 1;
                else if (board[row][col] == 'F')
                    return manhattanGrade(board, row, moveRight, Direction.Right) + 1;
                else if (board[row][col] == '7')
                    return manhattanGrade(board, row, moveLeft, Direction.Left) + 1;
                else
                    return 0;
            case Down:
                if (board[row][col] == '|')
                    return manhattanGrade(board, moveDown, col, Direction.Down) + 1;
                else if (board[row][col] == 'L')
                    return manhattanGrade(board, row, moveRight, Direction.Right) + 1;
                else if (board[row][col] == 'J')
                    return manhattanGrade(board, row, moveLeft, Direction.Left) + 1;
                else
                    return 0;
            case Left:
                if (board[row][col] == '-')
                    return manhattanGrade(board, row, moveLeft, Direction.Left) + 1;
                else if (board[row][col] == 'L')
                    return manhattanGrade(board, moveUp, col, Direction.Up) + 1;
                else if (board[row][col] == 'F')
                    return manhattanGrade(board, moveDown, col, Direction.Down) + 1;
                else
                    return 0;
            case Right:
                if (board[row][col] == '-')
                    return manhattanGrade(board, row, moveRight, Direction.Right) + 1;
                else if (board[row][col] == '7')
                    return manhattanGrade(board, moveDown, col, Direction.Down) + 1;
                else if (board[row][col] == 'J')
                    return manhattanGrade(board, moveUp, col, Direction.Up) + 1;
                else
                    return 0;
            default:
                return 0;
        }


    }

    enum Direction {
        Up, Down, Left, Right, Start, End;
    }
}
