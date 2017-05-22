package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */

public class Multiply<T> extends BinaryOperation<T> implements TripleExpression<T> {

    public Multiply(TripleExpression<T> firstOperand, TripleExpression<T> secondOperand, Operation<T> operation) {
        super(firstOperand, secondOperand, operation);
    }


    @Override
    protected T operation(T a, T b) throws MathExcpetion, OverflowException {
        return operation.multiply(a, b);
    }
}