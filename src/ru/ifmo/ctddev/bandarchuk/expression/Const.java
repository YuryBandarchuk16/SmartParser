package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class Const<T> implements TripleExpression<T> {
    private T value;

    public Const(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z) throws OverflowException, DivisionByZeroException {
        return value;
    }
}
