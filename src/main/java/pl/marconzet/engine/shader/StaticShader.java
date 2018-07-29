package pl.marconzet.engine.shader;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class StaticShader extends ShaderProgram {

    private static final String frag_reference = "fragmentShader.glsl";
    private static final String ver_reference = "vertexShader.glsl";

    public StaticShader() {
        super(ver_reference, frag_reference);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }
}
