package ru.ifmo.ctddev.bandarchuk.expression;

import java.util.EnumSet;

/**
 * Created by YuryBandarchuk on 3/27/17.
 */
public class ExpressionParser<T> implements Parser<T> {

    private int index;
    private T number;
    private Const<T> ZERO;
    private State state;
    private String expression;
    private String variableName;
    private Operation<T> operation;

    public ExpressionParser(final Operation<T> operation, final Const<T> ZERO) {
        this.operation = operation;
        this.ZERO = ZERO;
    }

    private char currentChar() {
        if (index < expression.length()) {
            return expression.charAt(index);
        }
        return '@';
    }

    private char getNextNonWhiteSpaceCharacter() {
        while (Character.isWhitespace(currentChar())) {
            index++;
        }
        char result = currentChar();
        index++;
        return result;
    }

    private T getNextNumber(int addToStartIndex) throws OverflowException {
        int startIndex = index + addToStartIndex;
        while (Character.isDigit(currentChar())) {
            index++;
        }
        return operation.parse(expression.substring(startIndex, index));
    }

    private void updateState() throws IncorrectExpressionException, OverflowException {
        char currentChar = getNextNonWhiteSpaceCharacter();
        switch (currentChar) {
            case '+':
                state = State.PLUS_SIGN;
                break;
            case '-':
                state = State.MINUS_SIGN;
                break;
            case '*':
                state = State.TIMES_SIGN;
                break;
            case '/':
                state = State.DIVIDE_SIGN;
                break;
            case '(':
                state = State.LEFT_BRACKET;
                break;
            case ')':
                state = State.RIGHT_BRACKET;
                break;
            case 'x':case 'y':case 'z':
                variableName = Character.toString(currentChar);
                state = State.VARIABLE;
                break;
            default:
                index--;
                if (Character.isDigit(currentChar)) {
                    number = getNextNumber(0);
                    state = State.CONSTANT;
                    break;
                } else if (index < expression.length() && expression.charAt(index) == 'm' && index + 2 < expression.length() && expression.substring(index, index + 3).equals("mod")) {
                    index += 3;
                    state = State.MOD;
                } else if (index < expression.length() && expression.charAt(index) == 'a' && index + 2 < expression.length() && expression.substring(index, index + 3).equals("abs")) {
                    index += 3;
                    state = State.ABS;
                } else if (index < expression.length() && expression.charAt(index) == 's' && index + 6 < expression.length() && expression.substring(index, index + 6).equals("square")) {
                    index += 6;
                    state = State.SQUARE;
                }
                //throw new IncorrectExpressionException("incorrect expression");
                break;
        }
    }


    private TripleExpression<T>  getNextFactor() throws IncorrectExpressionException, OverflowException {
        updateState();
        TripleExpression<T> result = null;
        switch (state) {
            case CONSTANT:
                result = new Const<>(number);
                updateState();
                break;
            case VARIABLE:
                result = new Variable<>(variableName);
                updateState();
                break;
            case LEFT_BRACKET:
                result = parseTerms();
                updateState();
                break;
            case MINUS_SIGN:
                result = new Subtract<>(ZERO, getNextFactor(), operation);
                break;
            case PLUS_SIGN:
                result = getNextFactor();
                break;
            case ABS:
                result = new Abs<>(getNextFactor(), operation);
                break;
            case SQUARE:
                result = new Square<>(getNextFactor(), operation);
                break;
            default:
                break;
        }
        return result;
    }

    private TripleExpression<T>  getNextTerm() throws IncorrectExpressionException, OverflowException {
        TripleExpression<T> currentFactor = getNextFactor();
        while (true) {
            switch (state) {
                case TIMES_SIGN:
                    currentFactor = new Multiply<>(currentFactor, getNextFactor(), operation);
                    break;
                case DIVIDE_SIGN:
                    currentFactor = new Divide<>(currentFactor, getNextFactor(), operation);
                    break;
                case MOD:
                    currentFactor = new Mod<>(currentFactor, getNextFactor(), operation);
                default:
                    return currentFactor;
            }
        }
    }

    private TripleExpression<T>  parseTerms() throws IncorrectExpressionException, OverflowException {
        TripleExpression<T> currentTerm = getNextTerm();
        while (true) {
            switch (state) {
                case PLUS_SIGN:
                    currentTerm = new Add<>(currentTerm, getNextTerm(), operation);
                    break;
                case MINUS_SIGN:
                    currentTerm = new Subtract<>(currentTerm, getNextTerm(), operation);
                    break;
                default:
                    return currentTerm;
            }
        }
    }


    public TripleExpression<T> parse(String expression) throws IncorrectExpressionException, OverflowException {
        this.index = 0;
        this.state = State.NONE;
        this.expression = expression;
        return parseTerms();
    }
}
