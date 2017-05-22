package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/19/17.
 */
public abstract class UnaryOperation<T> implements TripleExpression<T> {

    protected TripleExpression<T> argument;
    protected Operation<T> operation;

    public UnaryOperation(TripleExpression<T> argument, Operation<T> operation) {
        this.argument = argument;
        this.operation = operation;
    }

    @Override
    public T evaluate(T x, T y, T z) throws OverflowException, DivisionByZeroException {
        return operation(argument.evaluate(x, y, z));
    }

    protected abstract T operation(T a) throws OverflowException, DivisionByZeroException;
}
