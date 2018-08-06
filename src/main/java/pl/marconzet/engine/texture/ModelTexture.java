package pl.marconzet.engine.texture;

public class ModelTexture {

    private int textureID;

    private float shineDamper = 1;
    private float reflectivity = 0;

    private boolean transparency = false;
    private boolean fakeLighting = false;

    public ModelTexture(int textureID) {
        this.textureID = textureID;
    }

    public int getTextureID() {
        return textureID;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public ModelTexture setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
        return this;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public ModelTexture setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
        return this;
    }

    public boolean isTransparency() {
        return transparency;
    }

    public ModelTexture setTransparency(boolean transparency) {
        this.transparency = transparency;
        return this;
    }

    public boolean isFakeLighting() {
        return fakeLighting;
    }

    public ModelTexture setFakeLighting(boolean fakeLighting) {
        this.fakeLighting = fakeLighting;
        return this;
    }
}
