package pl.marconzet.engine;

import org.joml.Vector3f;
import pl.marconzet.engine.entity.Camera;
import pl.marconzet.engine.entity.Entity;
import pl.marconzet.engine.loader.Loader;
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

        RawModel model = loader.loadFromObj(Test.class.getResourceAsStream("models/stall.obj"));
        ModelTexture texture = new ModelTexture(loader.loadTexture(Test.class.getResourceAsStream("texture/stallTexture.png")));
        TextureModel textureModel = new TextureModel(model, texture);

        TransformationProperty property = new TransformationProperty();
        property.increasePosition(new Vector3f(0, 0, -10f));
        property.getScale().mul(0.5f);
        Entity entity = new Entity(textureModel, property);

        Camera camera = new Camera(new CameraProperty(shader), new TransformationProperty());

        while(!display.isCloseRequested()){
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
