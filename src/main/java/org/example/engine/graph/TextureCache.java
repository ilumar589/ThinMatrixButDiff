package org.example.engine.graph;

import org.eclipse.collections.impl.map.mutable.UnifiedMap;

public final class TextureCache {
    public static final String DEFAULT_TEXTURE = "/models/default/default_texture.png";
    private UnifiedMap<String, Texture> textureMap;

    public TextureCache() {
        textureMap = UnifiedMap.newMap();
        textureMap.put(DEFAULT_TEXTURE, Texture.createTexture(DEFAULT_TEXTURE));
    }

    public void cleanup() {
        textureMap.values().forEach(Texture::cleanup);
    }

    public Texture getTexture(String texturePath) {
        if (texturePath == null || texturePath.isEmpty()) {
            return textureMap.get(DEFAULT_TEXTURE);
        }

        return textureMap.get(texturePath);
    }
}
