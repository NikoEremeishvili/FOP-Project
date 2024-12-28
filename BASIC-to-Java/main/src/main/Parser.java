// Classes for parsed statements
package main;

import java.util.List;


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
            int value = Integer.parseInt(tokens.get(3)); 
            return new AssignmentStatement(variable, new NumberExpression(value));
        } else if (command.equals("PRINT")) {
            String variable = tokens.get(1);
            return new PrintStatement(new VariableExpression(variable));
        }

        throw new IllegalArgumentException("Unsupported command: " + command);
    }
}
