package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/12/17.
 */
public class UnsignedIntegerOperation implements Operation<Integer> {

    @Override
    public Integer parse(String expression) {
        if (expression.charAt(0) != '-') {
            return Integer.parseUnsignedInt(expression);
        } else {
            return Integer.parseInt(expression);
        }
    }

    @Override
    public Integer abs(Integer a) throws OverflowException {
        return Math.abs(a);
    }

    @Override
    public Integer square(Integer a) throws OverflowException {
        return a * a;
    }

    @Override
    public Integer mod(Integer a, Integer b) throws OverflowException, DivisionByZeroException {
        if (b == 0) {
            throw new DivisionByZeroException("while taking mod of " + a + " and " + b);
        }
        return a % b;
    }

    @Override
    public Integer add(Integer a, Integer b) throws OverflowException {
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) throws OverflowException {
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) throws OverflowException {
        return a * b;
    }

    @Override
    public Integer divide(Integer a, Integer b) throws DivisionByZeroException, OverflowException {
        if (b == 0) {
            throw new DivisionByZeroException(a + " was tried to divide by " + b);
        }
        return a / b;
    }

    @Override
    public Const<Integer> getZero() {
        return new Const<>(0);
    }
}
