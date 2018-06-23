package models;

import java.awt.*;

public class Step {
    private Point point;
    private int clicksNum;

    public Step(int rowNum, int colNum, int clicksNum) {
        this(new Point(rowNum, colNum), clicksNum);
    }

    public Step(Point point, int clicksNum) {
        this.point = point;
        this.clicksNum = clicksNum;
    }

    public Step() {
        this(new Point(), 0);
    }

    public Point getPoint() {
        return point;
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
        builder.append(point.x);
        builder.append(",");
        builder.append(point.y);
        builder.append(",");
        builder.append(clicksNum);
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return point == ((Step) obj).point;
    }
}
