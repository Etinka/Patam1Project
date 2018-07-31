package models;

import java.io.Serializable;

public interface State<T> extends Comparable<State<T>>, Serializable {
    T getState();

    void setCameFrom(State<T> n);

    State<T> getCameFrom();

    Step getStep();

    double getCost();

    void setCost(double cost);

    void printState();

    double getGrade();

    void setGrade(double grade);
}
