package pl.marconzet.engine;

import org.lwjgl.opengl.GL11;
import pl.marconzet.engine.entity.Camera;
import pl.marconzet.engine.entity.Entity;
import pl.marconzet.engine.entity.Light;
import pl.marconzet.engine.models.TextureModel;
import pl.marconzet.engine.shader.StaticShader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {
    private StaticShader shader;
    private EntityRenderer entityRenderer;

    private Map<TextureModel, List<Entity>> entities = new HashMap<>();

    public MasterRenderer(StaticShader shader) {
        enableCulling();
        this.shader = shader;
        this.entityRenderer = new EntityRenderer(shader);
    }

    public static void enableCulling(){
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }

    public static void disableCulling(){
        GL11.glDisable(GL11.GL_CULL_FACE);
    }

    public void render(Light light, Camera camera){
        entityRenderer.prepare();
        shader.start();
        shader.loadLight(light);
        shader.loadViewMatrix(camera);
        entityRenderer.render(entities);
        shader.stop();
        entities.clear();
    }

    public void processEntity(Entity entity){
        TextureModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if(batch != null){
            batch.add(entity);
        }else{
            List<Entity> newBatch = new ArrayList<Entity>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
        }
    }

    public void cleanUp(){
        shader.cleanUp();
    }
}
