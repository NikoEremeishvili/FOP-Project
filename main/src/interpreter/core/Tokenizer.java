package interpreter.core;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    /**
     * Splits a line of Tiny BASIC code into tokens.
     * Handles:
     * - Quoted strings as single tokens.
     * - Commas and semicolons as separate tokens (unless inside quotes).
     * - Whitespace as delimiters (unless inside quotes).
     */
    public List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        boolean insideQuotes = false;
        StringBuilder token = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '\"') {
                // Toggle insideQuotes mode
                insideQuotes = !insideQuotes;
                token.append(c);
            } else if ((c == ',' || c == ';') && !insideQuotes) {
                // Commas and semicolons outside quotes are separate tokens
                if (token.length() > 0) {
                    tokens.add(token.toString().trim());
                    token.setLength(0);
                }
                tokens.add(String.valueOf(c)); // Add the punctuation as a token
            } else if (Character.isWhitespace(c) && !insideQuotes) {
                // Split on whitespace if not inside quotes
                if (token.length() > 0) {
                    tokens.add(token.toString().trim());
                    token.setLength(0);
                }
            } else {
                // Append any other character
                token.append(c);
            }
        }

        // Add the last token if any
        if (token.length() > 0) {
            tokens.add(token.toString().trim());
        }

        return tokens;
    }
}
