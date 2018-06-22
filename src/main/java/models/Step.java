package models;

public class Step {
    private int colNum;
    private int rowNum;
    private int clicksNum;

    public Step(int colNum, int rowNum, int clicksNum) {
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
        builder.append(rowNum);
        builder.append(",");
        builder.append(colNum);
        builder.append(",");
        builder.append(clicksNum);
        return builder.toString();
    }
}
