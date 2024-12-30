package interpreter.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import interpreter.core.Statement;
import interpreter.core.Tokenizer;
import interpreter.execution.Executor;
import interpreter.parser.Parser;

public class Main {
    public static void main(String[] args) {
        // Instantiate the Tokenizer, Parser, and Executor
        Tokenizer tokenizer = new Tokenizer();
        Parser parser = new Parser();
        Executor executor = new Executor();

        // Specify the input file for the Tiny BASIC program
        String fileName = "program.txt";
        List<String> lines;

        try {
            // Read all lines from the specified file
            lines = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Prepare the program (list of statements)
        List<Statement> program = new ArrayList<>();
        for (String line : lines) {
            try {
                // Tokenize the line
                List<String> tokens = tokenizer.tokenize(line);

                // Parse the tokens into a Statement object
                Statement statement = parser.parse(tokens);

                // Add the statement to the program
                program.add(statement);
            } catch (Exception e) {
                // Handle syntax errors and invalid statements
                System.err.println("Syntax error in line: " + line);
                System.err.println("Details: " + e.getMessage());
            }
        }

        try {
            // Execute the program and handle runtime errors
            executor.execute(program);
        } catch (Exception e) {
            // Handle runtime errors like division by zero
            System.err.println("Runtime error: " + e.getMessage());
        }

        // Additional debug information for verification
        System.out.println("Program execution completed.");
    }
}