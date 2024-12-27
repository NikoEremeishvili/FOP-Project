import java.util.*;

public class Tokenizer {
    // Splitting BASIC into tokens.
    public List<String> tokenize(String line) {
        return Arrays.asList(line.split("\\s+"));
    }
}

