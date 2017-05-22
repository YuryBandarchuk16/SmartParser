package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class Subtract<T> extends BinaryOperation<T> implements TripleExpression<T> {

    public Subtract(TripleExpression<T> firstOperand, TripleExpression<T> secondOperand, Operation<T> operation) {
        super(firstOperand, secondOperand, operation);
    }


    @Override
    protected T operation(T a, T b) throws OverflowException {
        return operation.subtract(a, b);
    }
}