package pl.marconzet.engine;

import org.joml.Vector3f;
import pl.marconzet.engine.entity.Camera;
import pl.marconzet.engine.entity.Entity;
import pl.marconzet.engine.models.RawModel;
import pl.marconzet.engine.models.TextureModel;
import pl.marconzet.engine.properties.CameraProperty;
import pl.marconzet.engine.properties.TransformationProperty;
import pl.marconzet.engine.shader.StaticShader;
import pl.marconzet.engine.texture.ModelTexture;

/**
 * Created by MarconZet on 26.07.2018.
 */
public class Test {
    private static DisplayManager display = new DisplayManager();

    public static void main(String[] args) {

        display.createDisplay();
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer();

        float[] vertices = {
                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,0.5f,-0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,-0.5f,0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                0.5f,0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                -0.5f,-0.5f,0.5f,
                -0.5f,0.5f,0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,0.5f,-0.5f,
                0.5f,0.5f,-0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,-0.5f,0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f

        };

        float[] textureCoords = {

                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0


        };

        int[] indices = {
                0,1,3,
                3,1,2,
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22

        };

        RawModel model = loader.loadToVAO(vertices,textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture(Test.class.getResourceAsStream("tex.png")));
        TextureModel textureModel = new TextureModel(model, texture);

        TransformationProperty property = new TransformationProperty();
        property.increasePosition(new Vector3f(0, 0, -5f));
        Entity entity = new Entity(textureModel, property);

        Camera camera = new Camera(new CameraProperty(shader), new TransformationProperty());

        while(!display.isCloseRequested()){
            property.increaseRotation(new Vector3f((float) Math.PI/64, (float) Math.PI/64, 0f));
            camera.move(display.getWindowHandle());
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
            shader.stop();
            display.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        display.closeDisplay();

    }}
