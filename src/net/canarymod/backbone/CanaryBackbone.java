package net.canarymod.backbone;

public class CanaryBackbone implements Backbone {

    protected System system;
    protected Type type;
    
    public CanaryBackbone(System system, Type type) {
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
    public Backbone getBackbone(System system, Type type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Backbone getBackbone(System system) {
        // TODO Auto-generated method stub
        return null;
    }

}
