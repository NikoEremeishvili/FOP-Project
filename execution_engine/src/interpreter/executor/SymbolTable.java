package interpreter.executor;


import java.util.HashMap;
import java.util.Map;

/**
 * Represents a symbol table for managing variables and their values.
 */
public class SymbolTable {
    private Map<String, Integer> variables;

    public SymbolTable() {
        this.variables = new HashMap<>();
    }

    /**
     * Assigns a value to a variable.
     * 
     * @param name  The name of the variable.
     * @param value The value to assign.
     */
    public void set(String name, int value) {
        variables.put(name, value);
    }

    /**
     * Retrieves the value of a variable.
     * 
     * @param name The name of the variable.
     * @return The value of the variable.
     * @throws RuntimeException If the variable is not defined.
     */
    public int get(String name) {
        if (!variables.containsKey(name)) {
            throw new RuntimeException("Variable '" + name + "' is not defined.");
        }
        return variables.get(name);
    }

    /**
     * Checks if a variable exists in the symbol table.
     * 
     * @param name The name of the variable.
     * @return True if the variable exists, false otherwise.
     */
    public boolean contains(String name) {
        return variables.containsKey(name);
    }
}
