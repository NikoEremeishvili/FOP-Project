package interpreter.execution;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Integer> variables;

    public SymbolTable() {
        this.variables = new HashMap<>();
    }

    public void set(String name, int value) {
        variables.put(name, value);
    }

    public int get(String name) {
        if (!variables.containsKey(name)) {
            throw new RuntimeException("Variable '" + name + "' is not defined.");
        }
        int value = variables.get(name);
        return value;
    }

    public boolean contains(String name) {
        return variables.containsKey(name);
    }
}