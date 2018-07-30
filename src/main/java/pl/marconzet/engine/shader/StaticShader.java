package pl.marconzet.engine.shader;

import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram {

    private static final String frag_reference = "fragmentShader.glsl";
    private static final String ver_reference = "vertexShader.glsl";
    private int location_transformationMatrix;

    public StaticShader() {
        super(ver_reference, frag_reference);
    }

    @Override
    protected void getAllUniformsLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    public void loadTransMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }
}
