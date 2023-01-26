package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class ResourceLoader {

    private ResourceLoader() {
    }

    public static String loadShader(String shaderPath) {
        StringBuilder builder = new StringBuilder();

        try (InputStream in = ResourceLoader.class.getResourceAsStream(shaderPath)) {
            assert in != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return builder.toString();
    }
}
