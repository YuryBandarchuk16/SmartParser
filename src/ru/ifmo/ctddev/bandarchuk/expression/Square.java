package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/19/17.
 */
public class Square<T> extends UnaryOperation<T> implements TripleExpression<T> {
    public Square(TripleExpression<T> firstOperand, Operation<T> operation) {
        super(firstOperand, operation);
    }

    @Override
    protected T operation(T a) throws OverflowException {
        return operation.square(a);
    }
}
