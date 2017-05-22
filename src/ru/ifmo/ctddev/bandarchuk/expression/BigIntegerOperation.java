package ru.ifmo.ctddev.bandarchuk.expression;

import java.math.BigInteger;

/**
 * Created by YuryBandarchuk on 4/12/17.
 */
public class BigIntegerOperation implements Operation<BigInteger> {
    @Override
    public BigInteger parse(String expression) {
        return new BigInteger(expression);
    }

    @Override
    public BigInteger abs(BigInteger a) throws OverflowException {
        return a.abs();
    }

    @Override
    public BigInteger square(BigInteger a) throws OverflowException {
        return a.multiply(a);
    }

    @Override
    public BigInteger mod(BigInteger a, BigInteger b) throws OverflowException, DivisionByZeroException {
        if (b.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException("while performing " + a + " mod " + b);
        }
        return a.remainder(b);
    }

    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) throws DivisionByZeroException {
        if (b.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException("while dividing " + a + " by 0");
        }
        return a.divide(b);
    }

    @Override
    public Const<BigInteger> getZero() {
        return new Const<>(BigInteger.ZERO);
    }
}
