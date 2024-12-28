package main;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a symbol table for managing variables and their values.
 */
public class SymbolTable {
    private Map<String, Expression> variables;

    public SymbolTable() {
        this.variables = new HashMap<>();
    }

    /**
     * Assigns a value to a variable.
     * 
     * @param name  The name of the variable.
     * @param value The value to assign.
     */
    public void set(String name, Expression value) {
        variables.put(name, value);
    }

    /**
     * Retrieves the value of a variable.
     * 
     * @param variableExpression The VariableExpression representing the variable.
     * @return The Expression associated with the variable.
     * @throws RuntimeException If the variable is not defined.
     */
    public Expression get(VariableExpression variableExpression) {
        String name = variableExpression.name; // Extract the name from the VariableExpression
        if (!variables.containsKey(name)) {
            throw new RuntimeException("Variable '" + name + "' is not defined.");
        }
        return variables.get(name);
    }

    /**
     * Retrieves the numeric value of a variable (if it is a NumberExpression).
     * 
     * @param variableExpression The VariableExpression representing the variable.
     * @return The numeric value of the variable.
     * @throws RuntimeException If the variable is not defined or is not a NumberExpression.
     */
    public int getValue(VariableExpression variableExpression) {
        Expression expression = get(variableExpression);
        if (expression instanceof NumberExpression) {
            return ((NumberExpression) expression).value;
        }
        throw new RuntimeException("Unsupported expression type for variable: " + variableExpression.name);
    }

    /**
     * Checks if a variable exists in the table.
     * 
     * @param variableExpression The VariableExpression representing the variable.
     * @return True if the variable exists, false otherwise.
     */
    public boolean contains(VariableExpression variableExpression) {
        return variables.containsKey(variableExpression.name);
    }
}