package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class ResourceLoader {
    private static final String SHADER_LOCATION_PREFIX = "/assets/shaders/";
    private static final String SHADER_EXTENSION = ".glsl";

    private ResourceLoader() {}

    public static String loadShader(String shaderName) {
        StringBuilder builder = new StringBuilder();

        try(InputStream in = ResourceLoader
                .class
                .getResourceAsStream(SHADER_LOCATION_PREFIX.concat(shaderName).concat(SHADER_EXTENSION))) {
            assert in != null;
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

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
