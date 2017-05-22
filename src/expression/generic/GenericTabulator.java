package expression.generic;

import ru.ifmo.ctddev.bandarchuk.expression.*;

import java.math.BigInteger;

/**
 * Created by YuryBandarchuk on 4/12/17.
 */
public class GenericTabulator implements Tabulator {

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws IncorrectExpressionException, OverflowException {
        Operation<?> currentOperation;
        currentOperation = getOperation(mode);
        return calc(expression, currentOperation, x1, x2, y1, y2, z1, z2);
    }

    private Operation<?> getOperation(String mode) throws IncorrectExpressionException {
        switch (mode) {
            case "i":
                return new IntegerOperation();
            case "d":
                return new DoubleOperation();
            case "bi":
                currentOperation = new BigIntegerOperation();
                break;
            case "b":
                currentOperation = new ByteOperation();
                break;
            case "u":
                currentOperation = new UnsignedIntegerOperation();
                break;
            case "f":
                currentOperation = new FloatOperation();
                break;
            default:
                throw new IncorrectExpressionException("incorrect mode :'" + mode + "' found");
        }
        return currentOperation;
    }


    private <T> Object[][][] calc(String expression, Operation<T> currentOperation, int x1, int x2, int y1, int y2, int z1, int z2) throws IncorrectExpressionException, OverflowException {
        int xLength = x2 - x1 + 1;
        int yLength = y2 - y1 + 1;
        int zLength = z2 - z1 + 1;
        ExpressionParser<T> parser = new ExpressionParser<>(currentOperation, currentOperation.getZero());
        TripleExpression<T> parsed = parser.parse(expression);
        Object[][][] result = new Object[xLength][yLength][zLength];
        for (int addX = 0; addX < xLength; addX++) {
            for (int addY = 0; addY < yLength; addY++) {
                for (int addZ = 0; addZ < zLength; addZ++) {
                    try {
                        result[addX][addY][addZ] = parsed.evaluate(currentOperation.parse(Integer.toString(addX + x1)), currentOperation.parse(Integer.toString(addY + y1)), currentOperation.parse(Integer.toString(addZ + z1)));
                    } catch (OverflowException | DivisionByZeroException e) {
                        result[addX][addY][addZ] = null;
                    }
                }
            }
        }
        return result;
    }
}
