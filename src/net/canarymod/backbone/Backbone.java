package net.canarymod.backbone;

public class Backbone implements IBackbone {

    protected System system;
    protected Type type;
    
    public Backbone(System system, Type type) {
        this.system = system;
        this.type = type;
    }
    
    @Override
    public System getSystem() {
        return system;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public IBackbone getBackbone(System system, Type type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IBackbone getBackbone(System system) {
        // TODO Auto-generated method stub
        return null;
    }

}
