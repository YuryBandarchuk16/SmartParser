package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class Variable<T> implements TripleExpression<T> {

    private String variableName;

    public Variable(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        switch (variableName) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return null;
        }
    }
}
