package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/19/17.
 */
public class Mod<T> extends BinaryOperation<T> implements TripleExpression<T> {

    public Mod(TripleExpression<T> firstOperand, TripleExpression<T> secondOperand, Operation<T> operation) {
        super(firstOperand, secondOperand, operation);
    }


    @Override
    protected T operation(T a, T b) throws OverflowException, DivisionByZeroException {
        return operation.mod(a, b);
    }
}