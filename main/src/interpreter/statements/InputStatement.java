package interpreter.statements;

import interpreter.core.Statement;
import interpreter.execution.SymbolTable;

import java.util.Scanner;

/**
 * Represents an INPUT statement, e.g., INPUT "Enter a number: ", X.
 */
public class InputStatement implements Statement {
    private String prompt;
    private String variableName;

    public InputStatement(String prompt, String variableName) {
        this.prompt = prompt;
        this.variableName = variableName;
    }

    @Override
    public void execute(SymbolTable symbolTable) {
        System.out.print(prompt); // Display the prompt
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt(); // Read the integer input
        symbolTable.set(variableName, value); // Store the value in the symbol table
    }

    @Override
    public String toString() {
        return "INPUT \"" + prompt + "\", " + variableName;
    }
}