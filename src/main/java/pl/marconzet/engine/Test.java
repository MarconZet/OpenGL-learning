package pl.marconzet.engine;

import pl.marconzet.engine.models.RawModel;
import pl.marconzet.engine.models.TextureModel;
import pl.marconzet.engine.shader.StaticShader;
import pl.marconzet.engine.texture.ModelTexture;

import java.io.InputStream;

/**
 * Created by MarconZet on 26.07.2018.
 */
public class Test {
    private static DisplayManager display = new DisplayManager();

    public static void main(String[] args) {

        display.createDisplay();
        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices = {
                -0.5f,0.5f,0,	//V0
                -0.5f,-0.5f,0,	//V1
                0.5f,-0.5f,0,	//V2
                0.5f,0.5f,0		//V3
        };

        int[] indices = {
                0,1,3,	//Top left triangle (V0,V1,V3)
                3,1,2	//Bottom right triangle (V3,V1,V2)
        };

        float[] textureCoords = {
                0,0,
                0,1,
                1,1,
                1,0
        };

        RawModel model = loader.loadToVAO(vertices,textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture(Test.class.getResourceAsStream("tex.png")));
        TextureModel textureModel = new TextureModel(model, texture);


        while(!display.isCloseRequested()){
            //game logic
            renderer.prepare();
            shader.start();
            renderer.render(textureModel);
            shader.stop();
            display.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        display.closeDisplay();

    }}
