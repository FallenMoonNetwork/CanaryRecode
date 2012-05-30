package net.canarymod.api;

import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.Player;
import net.minecraft.server.OEntityTracker;

public class CanaryEntityTracker implements EntityTracker {
    private OEntityTracker tracker;
    
    public CanaryEntityTracker(OEntityTracker tracker) {
        this.tracker = tracker;
    }
    
    public OEntityTracker getHandle() {
        return tracker;
    }

    @Override
    public void trackEntity(Entity entity) {
        tracker.a(((CanaryEntity)entity).getHandle());

    }

    @Override
    public void untrackEntity(Entity entity) {
        tracker.b(((CanaryEntity)entity).getHandle());

    }

    @Override
    public void untrackPlayerSymmetrics(Player player) {
        tracker.a((((CanaryPlayer)player).getHandle()));
    }

    @Override
    public void updateTrackedEntities() {
        tracker.a();
    }

}
