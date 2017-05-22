package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/19/17.
 */
public class ByteOperation implements Operation<Byte> {
    @Override
    public Byte parse(String expression) {
        return (byte)Integer.parseInt(expression);
    }

    @Override
    public Byte abs(Byte a) throws OverflowException {
        return (byte)Math.abs(a);
    }

    @Override
    public Byte square(Byte a) throws OverflowException {
        return (byte)(a * a);
    }

    @Override
    public Byte mod(Byte a, Byte b) throws OverflowException, DivisionByZeroException {
        if (b == 0) {
            throw new DivisionByZeroException("while taking mod of " + a + " and " + b);
        }
        return (byte)(a % b);
    }

    @Override
    public Byte add(Byte a, Byte b) throws OverflowException {
        return (byte)(a + b);
    }

    @Override
    public Byte subtract(Byte a, Byte b) throws OverflowException {
        return (byte)(a - b);
    }

    @Override
    public Byte multiply(Byte a, Byte b) throws OverflowException {
        return (byte)(a * b);
    }

    @Override
    public Byte divide(Byte a, Byte b) throws DivisionByZeroException, OverflowException {
        if (b == 0) {
            throw new DivisionByZeroException(a + " was tried to divide by " + b);
        }
        return (byte)(a / b);
    }

    @Override
    public Const<Byte> getZero() {
        return new Const<>((byte)0);
    }
}
