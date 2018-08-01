package pl.marconzet.engine;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import pl.marconzet.engine.entity.Camera;
import pl.marconzet.engine.entity.Entity;
import pl.marconzet.engine.entity.Light;
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
        new Input(display.getWindowHandle());

        RawModel model = loader.loadFromObj(Test.class.getResourceAsStream("models/dragon.obj"));
        ModelTexture texture = new ModelTexture(loader.loadTexture(Test.class.getResourceAsStream("texture/texture.png")));
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        TextureModel textureModel = new TextureModel(model, texture);

        TransformationProperty property = new TransformationProperty();
        property.increasePosition(new Vector3f(0, 0, -5f));
        property.getScale().mul(0.2f);
        Entity entity = new Entity(textureModel, property);
        Light light = new Light(new Vector3f(5,0,10), new Vector3f(1,1,1));

        Camera camera = new Camera(new CameraProperty(shader), new TransformationProperty());

        MasterRenderer renderer = new MasterRenderer(shader);
        while(!display.isCloseRequested() && !Input.INSTANCE.isKeyPresed(GLFW.GLFW_KEY_ESCAPE)){
            camera.move();

            renderer.processEntity(entity);

            renderer.render(light, camera);
            display.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        display.closeDisplay();

    }}
