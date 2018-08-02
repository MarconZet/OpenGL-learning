package pl.marconzet.engine;

import org.joml.Vector3f;
import pl.marconzet.engine.entity.Camera;
import pl.marconzet.engine.entity.Entity;
import pl.marconzet.engine.entity.Light;
import pl.marconzet.engine.loader.Loader;
import pl.marconzet.engine.loader.ObjFile;
import pl.marconzet.engine.models.RawModel;
import pl.marconzet.engine.models.TexturedModel;
import pl.marconzet.engine.render.DisplayManager;
import pl.marconzet.engine.render.MasterRenderer;
import pl.marconzet.engine.terrain.Terrain;
import pl.marconzet.engine.texture.ModelTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager display = new DisplayManager();
		display.createDisplay();
		Loader loader = new Loader();
		
		
		RawModel model = ObjFile.read(MainGameLoop.class.getResourceAsStream("tree.obj"), true).toRawModel(loader);
		
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture(MainGameLoop.class.getResourceAsStream("tree.png"))));
		
		List<Entity> entities = new ArrayList<>();
		Random random = new Random();
		for(int i=0;i<500;i++){
			entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3));
		}
		
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture(MainGameLoop.class.getResourceAsStream("grass.png"))));
		Terrain terrain2 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture(MainGameLoop.class.getResourceAsStream("grass.png"))));
		
		Camera camera = new Camera();
		MasterRenderer renderer = new MasterRenderer();
		
		while(!display.isCloseRequested()){
			camera.move();
			
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			for(Entity entity:entities){
				renderer.processEntity(entity);
			}
			renderer.render(light, camera);
			display.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		display.closeDisplay();

	}

}
