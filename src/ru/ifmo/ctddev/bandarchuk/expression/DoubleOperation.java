package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/12/17.
 */
public class DoubleOperation implements Operation<Double> {
    @Override
    public Double parse(String expression) {
        return Double.parseDouble(expression);
    }

    @Override
    public Double abs(Double a) throws OverflowException {
        return Math.abs(a);
    }

    @Override
    public Double square(Double a) throws OverflowException {
        return a * a;
    }

    @Override
    public Double mod(Double a, Double b) throws OverflowException, DivisionByZeroException {
        return a % b;
    }

    @Override
    public Double add(Double a, Double b) throws OverflowException {
        return a + b;
    }

    @Override
    public Double subtract(Double a, Double b) throws OverflowException {
        return a - b;
    }

    @Override
    public Double multiply(Double a, Double b) throws OverflowException {
        return a * b;
    }

    @Override
    public Double divide(Double a, Double b) throws DivisionByZeroException, OverflowException {
        return a / b;
    }

    @Override
    public Const<Double> getZero() {
        return new Const<>(0.0);
    }
}
