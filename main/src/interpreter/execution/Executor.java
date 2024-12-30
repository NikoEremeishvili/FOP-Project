package interpreter.execution;

import interpreter.core.Statement;

import java.util.List;

public class Executor {
    private SymbolTable symbolTable;

    public Executor() {
        this.symbolTable = new SymbolTable();
    }

    /**
     * Executes the given program (a list of statements).
     *
     * @param program The list of statements to execute.
     */
    public void execute(List<Statement> program) {
        for (int i = 0; i < program.size(); i++) {
            Statement statement = program.get(i);
            
            try {
                // Execute the current statement
                statement.execute(symbolTable);
            } catch (RuntimeException e) {
                // Handle runtime errors gracefully
                System.err.println("Execution error: " + e.getMessage());
                break; // Stop execution on critical errors
            }
        }

        System.out.println("Program execution completed.");
    }
}
