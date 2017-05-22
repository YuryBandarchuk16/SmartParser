package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/12/17.
 */
public abstract class BinaryOperation<T> implements TripleExpression<T> {

    protected TripleExpression<T> first;
    protected TripleExpression<T> second;
    protected Operation<T> operation;

    public BinaryOperation(TripleExpression<T> first, TripleExpression<T> second, Operation<T> operation) {
        this.first = first;
        this.second = second;
        this.operation = operation;
    }

    @Override
    public T evaluate(T x, T y, T z) throws OverflowException, DivisionByZeroException {
        return operation(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    protected abstract T operation(T a, T b) throws OverflowException, DivisionByZeroException, MathExcpetion;
}
