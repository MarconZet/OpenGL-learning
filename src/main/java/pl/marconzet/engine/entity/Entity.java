package pl.marconzet.engine.entity;

import pl.marconzet.engine.models.TextureModel;
import pl.marconzet.engine.properties.TransformationProperty;

public class Entity {
    private TextureModel model;
    private TransformationProperty transformation;

    public Entity(TextureModel model, TransformationProperty transformation) {
        this.model = model;
        this.transformation = transformation;
    }

    public TextureModel getModel() {
        return model;
    }

    public void setModel(TextureModel model) {
        this.model = model;
    }

    public TransformationProperty getTransformation() {
        return transformation;
    }

    public void setTransformation(TransformationProperty transformation) {
        this.transformation = transformation;
    }
}
