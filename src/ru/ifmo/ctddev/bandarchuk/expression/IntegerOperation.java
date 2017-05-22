package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 4/12/17.
 */
public class IntegerOperation implements Operation<Integer> {
    @Override
    public Integer parse(String eapression) {
        return Integer.parseInt(eapression);
    }

    @Override
    public Integer abs(Integer a) throws OverflowException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("on taking abs of " + a);
        }
        return Math.abs(a);
    }

    @Override
    public Integer square(Integer a) throws OverflowException {
        if (a == 0) {
            return 0;
        }
        boolean condition = true;
        if (a >= 0) {
            condition = (a <= Integer.MAX_VALUE / a);
        } else if (a <= 0) {
            condition = (a >= Integer.MAX_VALUE / a);
        }
        if (!condition) {
            throw new OverflowException("overflow on squaring " + a);
        }
        return a * a;
    }

    @Override
    public Integer mod(Integer a, Integer b) throws OverflowException, DivisionByZeroException {
        if (b == 0) {
            throw new DivisionByZeroException("while performing " + a + " mod " + b);
        }
        return a % b;
    }

    @Override
    public Integer add(Integer a, Integer b) throws OverflowException {
        boolean condition = true;
        if (a >= 0 && b >= 0) {
            condition = (a <= Integer.MAX_VALUE - b);
        } else if (a <= 0 && b <= 0) {
            condition = (a >= Integer.MIN_VALUE - b);
        }
        if (!condition) {
            throw new OverflowException("on adding " + a + " and " + b);
        }
        return a + b;
    }

    @Override
    public Integer subtract(Integer x, Integer y) throws OverflowException {
        boolean condition = true;
        if (x <= 0 && y >= 0) {
            condition = (Integer.MIN_VALUE + y <= x);
        }
        if (x >= 0 && y <= 0) {
            condition = (x <= Integer.MAX_VALUE + y);
        }
        if (!condition) {
            throw new OverflowException("on subtraction " + y + " from " + x);
        }
        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) throws OverflowException {
        if (x == 0 || y == 0) {
            return 0;
        }
        boolean condition = true;
        if (x >= 0 && y >= 0) {
            condition = (x <= Integer.MAX_VALUE / y);
        } else if (x <= 0 && y <= 0) {
            condition = (x >= Integer.MAX_VALUE / y);
        } else if (x <= 0 && y >= 0) {
            condition = (x >= Integer.MIN_VALUE / y);
        } else if (x >= 0 && y <= 0) {
            condition = (y >= Integer.MIN_VALUE / x);
        }
        if (!condition) {
            throw new OverflowException("on multiplying " + x + " by " + y);
        }
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) throws DivisionByZeroException, OverflowException {
        if (y == 0) {
            throw new DivisionByZeroException(x + " was tried to divide by " + y);
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException("while dividing " + x + " by " + y);
        }
        return x / y;
    }

    @Override
    public Const<Integer> getZero() {
        return new Const<>(0);
    }
}
