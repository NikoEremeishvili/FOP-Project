import java.util.List;

public class Main {
    public static void main(String[] args) {
        String code = """
        LET x = 10
        LET y = 7
        PRINT x
        IF x > 5 THEN PRINT x
        WHILE x < 15 DO LET x = x + 1
        FOR i = 1 TO 5 DO PRINT i
        """;

        Tokenizer tokenizer = new Tokenizer();
        Parser parser = new Parser();

        // Starting line number
        int lineNumber = 10;

        for (String line : code.split("\n")) {
            if (line.trim().isEmpty()) continue; // Skips empty lines

            String numberedLine = lineNumber + " " + line;
            lineNumber += 10; // Increments each line number

            // Tokenizes and parses
            List<String> tokens = tokenizer.tokenize(numberedLine);
            Statement statement = parser.parse(tokens.subList(1, tokens.size()));

            System.out.println(numberedLine);
        }
    }
}


