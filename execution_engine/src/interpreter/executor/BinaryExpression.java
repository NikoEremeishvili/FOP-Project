package interpreter.executor;

/**
 * Represents a binary arithmetic expression, e.g., X + Y or 10 * 5.
 */
public class BinaryExpression extends Expression {
    private Expression left;
    private Expression right;
    private String operator;

    public BinaryExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public int evaluate(SymbolTable symbolTable) {
        int leftValue = left.evaluate(symbolTable);
        int rightValue = right.evaluate(symbolTable);

        switch (operator) {
            case "+":
                return leftValue + rightValue;
            case "-":
                return leftValue - rightValue;
            case "*":
                return leftValue * rightValue;
            case "/":
                if (rightValue == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                return leftValue / rightValue;
            case "%":
                return leftValue % rightValue;
            case "==":
                return leftValue == rightValue ? 1 : 0;
            case "!=":
                return leftValue != rightValue ? 1 : 0;
            case "<":
                return leftValue < rightValue ? 1 : 0;
            case "<=":
                return leftValue <= rightValue ? 1 : 0;
            case ">":
                return leftValue > rightValue ? 1 : 0;
            case ">=":
                return leftValue >= rightValue ? 1 : 0;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
}
