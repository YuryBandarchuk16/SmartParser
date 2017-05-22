package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/12/17.
 */
public class FloatOperation implements Operation<Float> {

    private static final float EPS = 1e-10f;

    @Override
    public Float parse(String expression) {
        return Float.parseFloat(expression);
    }

    @Override
    public Float abs(Float a) throws OverflowException {
        return Math.abs(a);
    }

    @Override
    public Float square(Float a) throws OverflowException {
        return a * a;
    }

    @Override
    public Float mod(Float a, Float b) throws OverflowException, DivisionByZeroException {
        return a % b;
    }

    @Override
    public Float add(Float a, Float b) throws OverflowException {
        return a + b;
    }

    @Override
    public Float subtract(Float x, Float y) throws OverflowException {
        return x - y;
    }

    @Override
    public Float multiply(Float x, Float y) throws OverflowException {
        return x * y;
    }

    @Override
    public Float divide(Float x, Float y) throws DivisionByZeroException, OverflowException {
        return x / y;
    }

    @Override
    public Const<Float> getZero() {
        return new Const<>(0.0f);
    }
}
