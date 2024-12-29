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

        //loops through each line of our inputed code
        for (String line : code.split("\n")) {
            List<String> tokens = tokenizer.tokenize(line); // Tokenize
            Statement statement = parser.parse(tokens); // Parse
            System.out.println(statement); // Print
        }
    }
}
