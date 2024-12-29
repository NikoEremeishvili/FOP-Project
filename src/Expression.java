// Class for  expressions
abstract class Expression {}

// Class for numbers
class NumberExpression extends Expression {
    int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

// Class for variable expressions (x)
class VariableExpression extends Expression {
    String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

// Binary expression class that can handle both arithmetic and relational operators
class BinaryExpression extends Expression {
    Expression left;
    Expression right;
    String operator;

    public BinaryExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return left + " " + operator + " " + right;
    }
}

