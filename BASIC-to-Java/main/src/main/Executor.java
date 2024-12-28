package main;

import java.util.List;

/**
 * The Executor class processes and executes a list of program statements.
 */
public class Executor {
    private SymbolTable symbolTable;

    public Executor() {
        this.symbolTable = new SymbolTable();
    }

    /**
     * Executes a list of statements.
     * 
     * @param program A list of statements representing the program.
     */
    public void execute(List<Statement> program) {
        for (Statement statement : program) {
            try {
                statement.execute(symbolTable);
            } catch (RuntimeException e) {
                System.err.println("Execution error: " + e.getMessage());
            }
        }
    }
}