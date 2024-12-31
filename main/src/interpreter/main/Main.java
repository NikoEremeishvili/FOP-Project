package interpreter.main;

import interpreter.core.Statement;
import interpreter.execution.Executor;
import interpreter.parser.Parser;
import interpreter.statements.LabeledStatement;
import interpreter.core.Tokenizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String filePath = "program.txt"; // Path to the program file

        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Path.of(filePath));

            // Initialize tokenizer and parser
            Tokenizer tokenizer = new Tokenizer();
            Iterator<String> lineIterator = lines.iterator();
            Parser parser = new Parser(lineIterator, tokenizer);

            // Parse the program
            List<Statement> program = new ArrayList<>();
            Set<Integer> labels = new HashSet<>(); // To detect duplicate labels

            while (lineIterator.hasNext()) {
                String line = lineIterator.next().trim();
                if (line.isEmpty()) continue; // Skip empty or whitespace-only lines

                // Tokenize the line
                List<String> tokens = tokenizer.tokenize(line);

                // Extract label (first token) and statement tokens
                int label;
                try {
                    label = Integer.parseInt(tokens.get(0));
                    if (!labels.add(label)) {
                        throw new IllegalArgumentException("Duplicate label detected: " + label);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid label: " + tokens.get(0));
                }

                List<String> statementTokens = tokens.subList(1, tokens.size()); // Remaining tokens

                // Parse the statement
                Statement statement = parser.parse(statementTokens);

                // Wrap the statement with the label
                LabeledStatement labeledStatement = new LabeledStatement(label, statement);
                program.add(labeledStatement);
            }

            // Sort program by label
            program.sort((s1, s2) -> Integer.compare(
                ((LabeledStatement) s1).getLabel(),
                ((LabeledStatement) s2).getLabel()
            ));

            // Execute the program
            Executor executor = new Executor();
            executor.execute(program);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing the program: " + e.getMessage());
            e.printStackTrace(); // Optional: Remove for production
        }
    }
}
