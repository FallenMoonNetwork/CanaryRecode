package net.canarymod.api;

import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.Player;
import net.canarymod.api.world.CanaryDimension;
import net.canarymod.api.world.Dimension;
import net.minecraft.server.OEntityTracker;

public class CanaryEntityTracker implements EntityTracker {
    private OEntityTracker tracker;
    
    private CanaryDimension dim;
    
    public CanaryEntityTracker(OEntityTracker tracker, CanaryDimension dim) {
        this.tracker = tracker;
        this.dim = dim;
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

    @Override
    public Dimension getAttachedDimension() {
        return dim;
    }

    @Override
    public void sendPacketToTrackedPlayer(Entity entity, Packet packet) {
        tracker.a(((CanaryEntity)entity).getHandle(), ((CanaryPacket)packet).getPacket());
    }

}
