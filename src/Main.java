import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Example
        String code = """
        LET x = 10
        PRINT x
        """;

        Tokenizer tokenizer = new Tokenizer();
        Parser parser = new Parser();

        // Iterate through each line of the input BASIC code.
        for (String line : code.split("\n")) {

            List<String> tokens = tokenizer.tokenize(line); // Tokenize the current line of BASIC code.


            Statement statement = parser.parse(tokens); // Parse the tokens


            System.out.println(statement); // Print the parsed thing
        }
    }
}

