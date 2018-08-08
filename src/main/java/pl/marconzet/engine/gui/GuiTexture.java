package pl.marconzet.engine.gui;

import org.joml.Matrix4f;
import org.joml.Vector2f;

public class GuiTexture {
    private int texture;
    private Vector2f position;
    private Vector2f scale;

    public GuiTexture(int texture, Vector2f position, Vector2f scale) {
        this.texture = texture;
        this.position = position;
        this.scale = scale;
    }

    public Matrix4f getTransformationMatrix(){
        return new Matrix4f()
                .identity()
                .translate(position.x, position.y, 0)
                .scale(scale.x, scale.y, 0);
    }

    public int getTexture() {
        return texture;
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getScale() {
        return scale;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
    }
}
