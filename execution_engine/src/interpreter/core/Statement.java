package interpreter.core;

import interpreter.execution.SymbolTable;

/**
 * Represents a general statement in the program.
 */
public interface Statement {
    /**
     * Executes the statement.
     * 
     * @param symbolTable The symbol table containing variables.
     */
    void execute(SymbolTable symbolTable);
    
    /**
     * Returns the label associated with the statement, or null if there is no label.
     */
    
    default Integer getLabel() {
        return null;
    }
}
