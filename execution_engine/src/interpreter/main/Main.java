package interpreter.main;

import interpreter.core.Statement;
import interpreter.execution.Executor;
import interpreter.statements.*;
import interpreter.expressions.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a program for Factorial of N
        List<Statement> program = new ArrayList<>();

        // 10 LET N = 5 (Replace 5 with the desired number)
        program.add(new LabeledStatement(10, new AssignmentStatement("N", new NumberExpression(5))));

        // 20 LET FACTORIAL = 1
        program.add(new LabeledStatement(20, new AssignmentStatement("FACTORIAL", new NumberExpression(1))));

        // 30 LET COUNT = 1
        program.add(new LabeledStatement(30, new AssignmentStatement("COUNT", new NumberExpression(1))));

        // 40 WHILE COUNT <= N
        List<Statement> whileBody = new ArrayList<>();

        // 50 LET FACTORIAL = FACTORIAL * COUNT
        whileBody.add(new AssignmentStatement(
            "FACTORIAL",
            new BinaryExpression(new VariableExpression("FACTORIAL"), new VariableExpression("COUNT"), "*")
        ));

        // 60 LET COUNT = COUNT + 1
        whileBody.add(new AssignmentStatement(
            "COUNT",
            new BinaryExpression(new VariableExpression("COUNT"), new NumberExpression(1), "+")
        ));

        // Add WHILE loop with label 40
        program.add(new LabeledStatement(40, new WhileStatement(
            new BinaryExpression(new VariableExpression("COUNT"), new VariableExpression("N"), "<="),
            whileBody
        )));

        // 70 PRINT FACTORIAL
        program.add(new LabeledStatement(70, new PrintStatement(new VariableExpression("FACTORIAL"))));

        // Execute the program
        Executor executor = new Executor();
        executor.execute(program);
    }
}