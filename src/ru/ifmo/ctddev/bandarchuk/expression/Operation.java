package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/12/17.
 */
public interface Operation<T> {
    T parse(String expression);
    T abs(T a) throws OverflowException;
    T square(T a) throws OverflowException;
    T mod(T a, T b) throws MathExcpetion
    T add(T a, T b) throws MathExcpetion;
    T subtract(T a, T b) throws MathExcpetion;
    T multiply(T a, T b) throws MathExcpetion;
    T divide(T a, T b) throws DivisionByZeroException, OverflowException;
    Const<T> getZero();
}
