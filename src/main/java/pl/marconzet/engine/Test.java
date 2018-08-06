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
    private static Loader loader = new Loader();

    public static void main(String[] args) {

        display.createDisplay();

        StaticShader shader = new StaticShader();
        new Input(display.getWindowHandle());

        String dragonModel = "models/dragon.obj";
        String dragonTexture = "texture/tex.png";
        Entity dragon = getEntity(dragonModel, dragonTexture);
        dragon.getTransformation().increasePosition(new Vector3f(0, 0, -5f)).setScale(new Vector3f().set(0.2f));
        dragon.getModel().getTexture().setReflectivity(3);

        String fernModel = "models/fern.obj";
        String fernTexture = "texture/fern.png";
        Entity fern = getEntity(fernModel, fernTexture);
        fern.getTransformation().setTranslation(new Vector3f(0, 0, -5f)).setScale(new Vector3f().set(0.2f));
        fern.getModel().getTexture().setTransparency(true);

        String grassModel = "models/grass.obj";
        String grassTexture = "texture/grass.png";
        Entity grass = getEntity(grassModel, grassTexture);
        grass.getTransformation().setScale(new Vector3f().set(0.1f)).setTranslation(new Vector3f(0, 0, -4));
        grass.getModel().getTexture().setTransparency(true).setFakeLighting(true).setReflectivity(0);

        Light light = new Light(new Vector3f(5,10,10), new Vector3f(1,1,1));
        Camera camera = new Camera(new CameraProperty(shader), new TransformationProperty());

        MasterRenderer renderer = new MasterRenderer(shader);
        while(!display.isCloseRequested() && !Input.INSTANCE.isKeyPresed(GLFW.GLFW_KEY_ESCAPE)){
            camera.move();

            renderer.processEntity(dragon);
            renderer.processEntity(grass);

            renderer.render(light, camera);
            display.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        display.closeDisplay();

    }

    private static Entity getEntity(String modelName, String textureName) {
        RawModel model = loader.loadFromObj(Test.class.getResourceAsStream(modelName));
        ModelTexture texture = new ModelTexture(loader.loadTexture(Test.class.getResourceAsStream(textureName)));
        texture.setShineDamper(10).setReflectivity(1);
        TextureModel textureModel = new TextureModel(model, texture);
        TransformationProperty transformation = new TransformationProperty();
        return new Entity(textureModel, transformation);
    }
}
