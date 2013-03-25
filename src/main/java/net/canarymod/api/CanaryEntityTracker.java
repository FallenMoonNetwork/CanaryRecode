package net.canarymod.api;


import java.util.ArrayList;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;


public class CanaryEntityTracker implements EntityTracker {
    private net.minecraft.server.EntityTracker tracker;

    private CanaryWorld dim;

    public CanaryEntityTracker(net.minecraft.server.EntityTracker tracker, CanaryWorld dim) {
        this.tracker = tracker;
        this.dim = dim;
    }

    public net.minecraft.server.EntityTracker getHandle() {
        return tracker;
    }

    @Override
    public void trackEntity(Entity entity) {
        tracker.a((net.minecraft.server.Entity) ((CanaryEntity) entity).getHandle());

    }

    @Override
    public void untrackEntity(Entity entity) {
        tracker.b((net.minecraft.server.Entity) ((CanaryEntity) entity).getHandle());

    }

    @Override
    public void untrackPlayerSymmetrics(Player player) {
        tracker.a((((CanaryPlayer) player).getHandle()));
    }

    @Override
    public void updateTrackedEntities() {
        tracker.a();
    }

    @Override
    public World getAttachedDimension() {
        return dim;
    }

    @Override
    public void sendPacketToTrackedPlayer(Entity entity, Packet packet) {
        tracker.a(((CanaryEntity) entity).getHandle(), ((CanaryPacket) packet).getPacket());
    }

    @Override
    public ArrayList<Entity> getTrackedEntities() {
        ArrayList<Entity> entities = new ArrayList<Entity>();

        for (net.minecraft.server.EntityTrackerEntry e : tracker.getTrackedEntities()) {
            entities.add(e.getCanaryTrackerEntry().getEntity());
        }
        return entities;
    }

}
