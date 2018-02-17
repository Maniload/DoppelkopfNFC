package de.craplezz.doppelkopfnfc.io;

import java.io.*;
import java.util.Arrays;

/**
 * @author Overload
 * @version 1.0
 */
public class ConsoleHandler {

    private final PrintStream outputStream = System.out;
    private final BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));

    public String waitForInput() throws IOException {
        return inputStream.readLine();
    }
    
    public String waitForMatch(String... possibilities) throws IOException {
        int index;
        while ((index = Arrays.binarySearch(possibilities, waitForInput())) < 0) {
            info(String.join(", ", possibilities));
        }
        return possibilities[index];
    }

    public void info(String... messages) {
        for (String message : messages) {
            outputStream.println(message);
        }
    }
    
}
