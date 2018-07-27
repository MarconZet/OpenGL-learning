package pl.marconzet.engine;

import pl.marconzet.engine.shader.StaticShader;

import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.glfw.GLFW.*;

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

        RawModel model = loader.loadToVAO(vertices,indices);

        while(!display.isCloseRequested()){
            //game logic
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            display.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        display.closeDisplay();

    }}
