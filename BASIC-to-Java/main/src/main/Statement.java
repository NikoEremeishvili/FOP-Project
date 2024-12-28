package main;

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
}
