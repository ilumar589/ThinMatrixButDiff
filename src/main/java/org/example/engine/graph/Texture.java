package org.example.engine.graph;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;

public record Texture(int textureId, String texturePath) {

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, textureId);
    }

    public void cleanup() {
        glDeleteTextures(textureId);
    }

    public static Texture createTexture(String texturePath) {
        int[] w = new int[1];
        int[] h = new int[1];
        int[] channels = new int[1];
        ByteBuffer textureReference = stbi_load(texturePath, w, h, channels, 4);

        if (textureReference == null) {
            throw new RuntimeException("Image file [" + texturePath + "] not loaded: " + stbi_failure_reason());
        }

        int generatedTextureId = generateTexture(w[0], h[0], textureReference);

        stbi_image_free(textureReference);

        return new Texture(generatedTextureId, texturePath);

    }

    /***
     *
     * @param width
     * @param height
     * @param textureReference
     * @return generated texture reference as an int value
     */
    private static int generateTexture(int width, int height, ByteBuffer textureReference) {
        int textureId = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, textureId);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, textureReference);
        glGenerateMipmap(GL_TEXTURE_2D);

        return textureId;
    }
}
