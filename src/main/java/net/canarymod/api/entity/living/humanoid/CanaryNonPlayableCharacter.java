package net.canarymod.api.entity.living.humanoid;


import net.canarymod.api.CanaryPacket;
import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.Packet20NamedEntitySpawn;
import net.minecraft.server.Packet29DestroyEntity;


/**
 * NonPlayableCharacter implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryNonPlayableCharacter extends CanaryEntityLiving implements NonPlayableCharacter {

    /**
     * Constructs a new wrapper for EntityNonPlayableCharacter
     * 
     * @param entity
     *            the EntityVillager to wrap
     * @param inHand
     *            the Item to set inHand
     */
    public CanaryNonPlayableCharacter(EntityNonPlayableCharacter npc, Item inHand) {
        super(npc);
        this.getHandle().setNPC(this);
        this.getInventory().setSlot(inHand);
        this.setItemInHandSlot(inHand != null ? inHand.getSlot() : 0);
    }

    /**
     * Constructs a new NonPlayableCharacter
     * 
     * @param name
     *            the Name to give to the NPC
     * @param world
     *            the World to spawn the NPC in
     * @param dim
     *            the Dimension of the World to spawn the NPC in
     * @param x
     *            the X coordinate to spawn the NPC at
     * @param y
     *            the Y coordinate to spawn the NPC at
     * @param z
     *            the Z coordinate to spawn the NPC at
     * @param rotation
     *            the rotation to spawn the NPC with
     * @param pitch
     *            the pitch to spawn the NPC with
     * @param inHand
     *            the Item to set in the NPC's hand
     */
    public CanaryNonPlayableCharacter(String name, World world, int dim, double x, double y, double z, float rotation, float pitch, Item inHand) {
        this(new EntityNonPlayableCharacter(name, world, x, y, z, rotation, pitch), inHand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return getHandle().bS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        getHandle().bS = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void teleportTo(Position position) {
        getHandle().a(position.getX(), position.getY(), position.getZ(), getRotation(), getPitch());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void teleportTo(Location location) {
        getHandle().a(location.getX(), location.getY(), location.getZ(), location.getRotation(), location.getPitch());
        if (this.getWorld() != location.getWorld()) {
            // I don't know yet
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void teleportTo(int x, int y, int z) {
        getHandle().a(x, y, z, getRotation(), getPitch());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItemHeld() {
        return getHandle().getPlayerInventory().getSlot(getHandle().bK.c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setItemInHandSlot(int slot) {
        if (slot > 0 && slot < 9) {
            getHandle().bK.c = slot;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {
        return getHandle().getPlayerInventory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ghost(Player player) {
        player.sendPacket(new CanaryPacket(new Packet29DestroyEntity(this.getID())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void haunt(Player player) {
        player.sendPacket(new CanaryPacket(new Packet20NamedEntitySpawn(this.getHandle())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lookAt(Player player) {
        double xDiff = player.getX() - getX();
        double yDiff = player.getY() - getY();
        double zDiff = player.getZ() - getZ();
        double DistanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        double DistanceY = Math.sqrt(DistanceXZ * DistanceXZ + yDiff * yDiff);
        double yaw = (Math.acos(xDiff / DistanceXZ) * 180 / Math.PI);
        double pitch = (Math.acos(yDiff / DistanceY) * 180 / Math.PI) - 90;

        if (zDiff < 0.0) {
            yaw = yaw + (Math.abs(180 - yaw) * 2);
        }
        teleportTo(new Location(this.getWorld(), getX(), getY(), getZ(), (float) yaw, (float) pitch));

        getHandle().bR = (float) yaw - 90; // Camera/Head Position
        getHandle().c(); // Update Entity
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lookAtNearest() {
        EntityPlayer ep = ((CanaryWorld) getWorld()).getHandle().a((net.minecraft.server.Entity) getHandle(), 25);
        if (ep != null && ((CanaryEntityLiving) ep.getCanaryEntity()).isPlayer()) {
            lookAt((Player) ep.getCanaryEntity());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NonPlayableCharacter despawn() {
        // TODO: Remove entity from trackers but do not destroy
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public void spawn() {
        super.spawn();
        // TODO: Special spawning stuffs
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityNonPlayableCharacter getHandle() {
        return (EntityNonPlayableCharacter) entity;
    }
}
