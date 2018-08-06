package pl.marconzet.engine;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import pl.marconzet.engine.entity.Entity;
import pl.marconzet.engine.models.RawModel;
import pl.marconzet.engine.models.TextureModel;
import pl.marconzet.engine.shader.StaticShader;
import pl.marconzet.engine.texture.ModelTexture;

import java.util.List;
import java.util.Map;

public class EntityRenderer {
    private StaticShader shader;

    public EntityRenderer(StaticShader shader) {
        this.shader = shader;
    }

    public void prepare(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0, 0, 1, 1);
    }

    public void render(Map<TextureModel, List<Entity>> entities){
        entities.forEach((x, y) ->{
            prepareTexturedModel(x);
            y.forEach(z ->{
                prepareInstance(z);
                GL11.glDrawElements(GL11.GL_TRIANGLES, z.getModel().getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

            });
            unbindTextureModel();
        });
    }

    private void prepareTexturedModel(TextureModel model){
        RawModel rawModel = model.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        ModelTexture texture = model.getTexture();
        if(texture.isTransparency()){
            MasterRenderer.disableCulling();
        }
        shader.loadFakeLighting(texture.isFakeLighting());
        shader.loadShineVaraibles(texture.getShineDamper(), texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
    }

    private void unbindTextureModel(){
        MasterRenderer.enableCulling();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(Entity entity){
        Matrix4f transformationMatrix = entity.getTransformation().toTransformationMatrix();
        shader.loadTransMatrix(transformationMatrix);
    }
}
