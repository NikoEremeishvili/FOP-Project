package interpreter.executor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a list of statements for a simple program
        List<Statement> program = new ArrayList<>();

        // LET X = 10
        program.add(new AssignmentStatement("X", new NumberExpression(10)));

        // LET Y = X + 5
        program.add(new AssignmentStatement("Y", new BinaryExpression(
            new VariableExpression("X"), new NumberExpression(5), "+")));

        // LET Z = Y * 2
        program.add(new AssignmentStatement("Z", new BinaryExpression(
            new VariableExpression("Y"), new NumberExpression(2), "*")));

        // PRINT X
        program.add(new PrintStatement(new VariableExpression("X")));

        // PRINT Y
        program.add(new PrintStatement(new VariableExpression("Y")));

        // PRINT Z
        program.add(new PrintStatement(new VariableExpression("Z")));

        // Execute the program
        Executor executor = new Executor();
        executor.execute(program);
    }
}
