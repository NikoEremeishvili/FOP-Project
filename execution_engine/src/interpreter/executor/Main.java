package interpreter.executor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a SymbolTable and Executor
        SymbolTable symbolTable = new SymbolTable();
        Executor executor = new Executor();

        // Define N = 5
        List<Statement> program = new ArrayList<>();
        program.add(new AssignmentStatement("N", new NumberExpression(5)));
        program.add(new AssignmentStatement("SUM", new NumberExpression(0)));
        program.add(new AssignmentStatement("I", new NumberExpression(1)));

        // WHILE I <= N DO SUM = SUM + I; I = I + 1
        List<Statement> whileBody = new ArrayList<>();
        whileBody.add(new AssignmentStatement("SUM", new BinaryExpression(
            new VariableExpression("SUM"), new VariableExpression("I"), "+")));
        whileBody.add(new AssignmentStatement("I", new BinaryExpression(
            new VariableExpression("I"), new NumberExpression(1), "+")));
        program.add(new WhileStatement(
            new BinaryExpression(new VariableExpression("I"), new VariableExpression("N"), "<="),
            whileBody
        ));

        // PRINT SUM
        program.add(new PrintStatement(new VariableExpression("SUM")));

        // Execute the program
        executor.execute(program);
    }
}