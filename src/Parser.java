// Classes for parsed statements
import java.util.List;

abstract class Statement { }

class AssignmentStatement extends Statement {
    String variable;
    Expression value;

    public AssignmentStatement(String variable, Expression value) {
        this.variable = variable;
        this.value = value;
    }

    public String toString() {
        return "LET " + variable + " = " + value;
    }
}

class PrintStatement extends Statement {
    Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }
    public String toString() {
        return "PRINT " + expression;
    }
}


abstract class Expression { }

class NumberExpression extends Expression {
    int value;

    public NumberExpression(int value) {
        this.value = value;
    }
    public String toString() {
        return Integer.toString(value);
    }
}

class VariableExpression extends Expression {
    String name;

    public VariableExpression(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

class Parser {
    public Statement parse(List<String> tokens) {
        String command = tokens.get(0);

        if (command.equals("LET")) {
            String variable = tokens.get(1);
            int value = Integer.parseInt(tokens.get(3)); // Assumes "LET x = 10"
            return new AssignmentStatement(variable, new NumberExpression(value));
        } else if (command.equals("PRINT")) {
            String variable = tokens.get(1);
            return new PrintStatement(new VariableExpression(variable));
        }

        throw new IllegalArgumentException("Unsupported command: " + command);
    }
}