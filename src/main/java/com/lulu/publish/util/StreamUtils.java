package com.lulu.publish.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Helper for handling streams.
 */
public final class StreamUtils {

    private StreamUtils() {
    }

    /**
     * Generate a string from the given input stream.
     *
     * @param inputStream input stream
     * @return string representation
     * @throws IOException if reading in the stream fails
     */
    public static String inputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        inputStream.close();
        return sb.toString();
    }

}
