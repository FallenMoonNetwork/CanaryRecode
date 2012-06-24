package net.canarymod.api;

import net.canarymod.api.entity.Entity;
import net.minecraft.server.OEntityTrackerEntry;

public class CanaryEntityTrackerEntry implements EntityTrackerEntry {
    private OEntityTrackerEntry entry;
    
    public CanaryEntityTrackerEntry(OEntityTrackerEntry entry) {
        this.entry = entry;
    }
    
    @Override
    public Entity getEntity() {
        return entry.a.getCanaryEntity();
    }

}
