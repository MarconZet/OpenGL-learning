package pl.marconzet.engine.shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import pl.marconzet.engine.entity.Camera;
import pl.marconzet.engine.entity.Light;

public class StaticShader extends ShaderProgram {

    private static final String frag_reference = "fragmentShader.glsl";
    private static final String ver_reference = "vertexShader.glsl";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColour;
    private int location_shineDamper;
    private int location_reflectivity;
    private int location_fakeLighting;
    private int location_skyColour;


    public StaticShader() {
        super(ver_reference, frag_reference);
    }

    @Override
    protected void getAllUniformsLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightColour = super.getUniformLocation("lightColour");
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_shineDamper = super.getUniformLocation("shineDamper");
        location_reflectivity = super.getUniformLocation("reflectivity");
        location_fakeLighting = super.getUniformLocation("fakeLighting");
        location_skyColour = super.getUniformLocation("skyColour");
    }


    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

    public void loadSkyColour(Vector3f colour){
        super.loadVector(location_skyColour, colour);
    }

    public void loadFakeLighting(boolean useFake){
        super.loadBoolean(location_fakeLighting, useFake);
    }

    public void loadTransMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f matrix = camera.getTransformationProperty().toViewMatrix();
        super.loadMatrix(location_viewMatrix, matrix);
    }

    public void loadLight(Light light){
        super.loadVector(location_lightColour, light.getColour());
        super.loadVector(location_lightPosition, light.getPosition());
    }

    public void loadShineVaraibles(float damper, float reflectivity){
        super.loadFloat(location_shineDamper, damper);
        super.loadFloat(location_reflectivity, reflectivity);
    }
}


