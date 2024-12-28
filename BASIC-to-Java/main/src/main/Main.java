package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Instantiate the Tokenizer, Parser, and Executor
        Tokenizer tokenizer = new Tokenizer();
        Parser parser = new Parser();
        Executor executor = new Executor();

        // Read the Tiny BASIC program from a file
        String fileName = "program.txt";
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Parse the program into a list of statements
        List<Statement> program = new ArrayList<>();
        for (String line : lines) {
            // Tokenize the line
            List<String> tokens = tokenizer.tokenize(line);

            // Parse the tokens into a Statement object
            Statement statement = parser.parse(tokens);

            // Add the statement to the program
            program.add(statement);
        }

        // Execute the program
        executor.execute(program);
    }
}