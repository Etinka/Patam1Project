package models;

import java.io.Serializable;

public abstract class Step implements Serializable {
    @Override
    public String toString()
    {
        return stepToString();
    }
    public abstract String stepToString();
}
