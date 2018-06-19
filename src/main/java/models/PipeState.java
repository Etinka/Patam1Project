package models;

public class PipeState {
    private int colNum;
    private int rowNum;
    private int clicksNum;

    public PipeState(int colNum, int rowNum, int clicksNum) {
        this.colNum = colNum;
        this.rowNum = rowNum;
        this.clicksNum = clicksNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getClicksNum() {
        return clicksNum;
    }

    public void setClicksNum(int clicksNum) {
        this.clicksNum = clicksNum;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Click on row number ");
        builder.append(rowNum);
        builder.append(", column number ");
        builder.append(colNum);
        builder.append(" ");
        builder.append(clicksNum);
        builder.append(" times");
        return builder.toString();
    }
}
