package net.canarymod.backbone;

import net.canarymod.database.Database;

public class CanaryBackbone implements Backbone {

    protected System system;
    protected Database.Type type;
    
    public CanaryBackbone(System system, Database.Type type) {
        this.system = system;
        this.type = type;
    }
    
    @Override
    public System getSystem() {
        return system;
    }

    @Override
    public Database.Type getType() {
        return type;
    }

    @Override
    public Backbone getBackbone(System system, Database.Type type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Backbone getBackbone(System system) {
        // TODO Auto-generated method stub
        return null;
    }

}
