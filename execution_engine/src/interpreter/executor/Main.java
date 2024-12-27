package interpreter.executor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a list of statements for a simple program
        List<Statement> program = new ArrayList<>();
        program.add(new AssignmentStatement("X", 2));
        program.add(new PrintStatement("X"));

        // Execute the program
        Executor executor = new Executor();
        executor.execute(program);
    }
}