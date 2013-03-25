package net.canarymod.api;


import net.canarymod.api.entity.Entity;


public class CanaryEntityTrackerEntry implements EntityTrackerEntry {
    private net.minecraft.server.EntityTrackerEntry entry;

    public CanaryEntityTrackerEntry(net.minecraft.server.EntityTrackerEntry entry) {
        this.entry = entry;
    }

    @Override
    public Entity getEntity() {
        return entry.a.getCanaryEntity();
    }

}
