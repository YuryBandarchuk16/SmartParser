package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/22/17.
 */
public interface TripleExpression<T> {
    T evaluate(T x, T y, T z) throws OverflowException, DivisionByZeroException;
}
