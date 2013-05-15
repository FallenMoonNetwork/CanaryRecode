package net.canarymod.api.entity.living.humanoid;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.canarymod.chat.Colors;
import net.minecraft.server.Packet16BlockItemSwitch;
import net.minecraft.server.Packet20NamedEntitySpawn;
import net.minecraft.server.Packet29DestroyEntity;


/**
 * NonPlayableCharacter implementation
 *
 * @author Jason (darkdiplomat)
 */
public class CanaryNonPlayableCharacter extends CanaryEntityLiving implements NonPlayableCharacter {
    private final List<NPCBehavior> behaviors;
    private String prefix = "<" + Colors.ORANGE + "NPC " + Colors.WHITE + "%name> ";

    /**
     * Constructs a new wrapper for EntityNonPlayableCharacter
     *
     * @param entity
     *            the EntityVillager to wrap
     * @param inHand
     *            the Item to set inHand
     */
    public CanaryNonPlayableCharacter(EntityNonPlayableCharacter npc) {
        super(npc);
        this.getHandle().setNPC(this);
        this.behaviors = Collections.synchronizedList(new ArrayList<NPCBehavior>());
    }

    /**
     * Constructs a new NonPlayableCharacter
     *
     * @param name
     *            the Name to give to the NPC
     * @param location
     *            the Location to put the NPC
     * @param inHand
     *            the Item to set in the NPC's hand
     */
    public CanaryNonPlayableCharacter(String name, Location location) {
        this(new EntityNonPlayableCharacter(name, location));
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
    public String getDisplayName() {
        return getHandle().getDisplayName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDisplayName(String name) {
        getHandle().setDisplayName(name);
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
        if (this.getWorld() != location.getWorld()) {// I don't know yet
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
            getHandle().a.b(new Packet16BlockItemSwitch(getHandle().bK.c));
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
    public void lookAt(Entity entity) {
        double xDiff = entity.getX() - getX();
        double yDiff = entity.getY() - getY();
        double zDiff = entity.getZ() - getZ();
        double DistanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        double DistanceY = Math.sqrt(DistanceXZ * DistanceXZ + yDiff * yDiff);
        double yaw = (Math.acos(xDiff / DistanceXZ) * 180 / Math.PI);
        double pitch = (Math.acos(yDiff / DistanceY) * 180 / Math.PI) - 90;

        if (zDiff < 0.0) {
            yaw = yaw + (Math.abs(180 - yaw) * 2);
        }
        this.setRotation((float) yaw - 90);
        this.setPitch((float) pitch);
        getHandle().bR = (float) yaw - 90; // Camera/Head Position
        getHandle().c(); // Update Entity
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lookAtNearest() {
        Player toLookAt = null;

        for (Player player : Canary.getServer().getPlayerList()) {
            if (player.getWorld().getName().equals(this.getWorld().getName())) {
                if (player.getWorld().getType() == this.getWorld().getType()) {
                    if (toLookAt != null) {
                        if (distanceTo(player) > 15) {
                            continue;
                        }
                        if (distanceTo(player) < distanceTo(toLookAt)) {
                            toLookAt = player;
                        }
                    } else {
                        toLookAt = player;
                    }
                }
            }
        }
        if (toLookAt != null) {
            lookAt(toLookAt);
        }
    }

    private double distanceTo(Player player) {
        double xDiff = player.getX() - getX();
        double yDiff = player.getY() - getY();
        double zDiff = player.getZ() - getZ();

        return xDiff * xDiff + yDiff * yDiff + zDiff * zDiff;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NonPlayableCharacter despawn() {
        getWorld().getEntityTracker().untrackEntity(this);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public boolean spawn() {
        if (super.spawn()) {
            // TODO: Special spawning stuffs ?
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<NPCBehavior> getBehaviors() {
        return this.behaviors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NPCBehavior removeBehavior(NPCBehavior behavior) {
        if (this.behaviors.contains(behavior)) {
            return this.behaviors.remove(behaviors.indexOf(behavior));
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addBehavior(NPCBehavior behavior) {
        return this.behaviors.add(behavior);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void chat(String msg) {
        Canary.getServer().broadcastMessage(prefix.replace("%name", getName()) + msg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void privateMessage(Player player, String msg) {
        player.sendMessage("[PM] " + prefix.replace("%name", getName()) + msg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrefix() {
        return prefix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInvulnerable(boolean invulnerable) {
        getHandle().ce.a = invulnerable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInvulnerable() {
        return getHandle().ce.a;
    }

    void update() {
        try {
            synchronized (behaviors) {
                for (NPCBehavior behavior : behaviors) {
                    try {
                        if (!getHandle().M) {
                            behavior.onUpdate();
                        }
                    } catch (Exception ex) {
                        Canary.logWarning("Exception while calling onUpdate in behavior" + behavior.getClass().getSimpleName() + " for NPC " + this.getName());
                        Canary.logStackTrace("", ex);
                    }
                }
            }
        } catch (Exception ex) {
            Canary.logWarning("Exception occured while calling update for NPC " + this.getName());
            Canary.logStackTrace("", ex);
        }
    }

    void clicked(Player player) {
        try {
            synchronized (behaviors) {
                for (NPCBehavior behavior : behaviors) {
                    try {
                        if (!getHandle().M) {
                            behavior.onClicked(player);
                        }
                    } catch (Exception ex) {
                        Canary.logWarning("Exception occured while calling onClicked in behavior" + behavior.getClass().getSimpleName() + " for NPC " + this.getName());
                        Canary.logStackTrace("", ex);
                    }
                }
            }
        } catch (Exception ex) {
            Canary.logWarning("Exception while calling clicked for NPC " + this.getName());
            Canary.logStackTrace("", ex);
        }
    }

    void attacked(CanaryEntity entity) {
        try {
            synchronized (behaviors) {
                for (NPCBehavior behavior : behaviors) {
                    try {
                        if (!getHandle().M) {
                            behavior.onAttacked(entity);
                        }
                    } catch (Exception ex) {
                        Canary.logWarning("Exception while calling onAttack in behavior" + behavior.getClass().getSimpleName() + " for NPC " + this.getName());
                    }
                }
            }
        } catch (Exception ex) {
            Canary.logWarning("Exception occured while calling attacked for NPC " + this.getName());
            Canary.logStackTrace("", ex);
        }
    }

    void destroyed() {
        try {
            synchronized (behaviors) {
                for (NPCBehavior behavior : behaviors) {
                    try {
                        behavior.onDestroy();
                    } catch (Exception ex) {
                        Canary.logWarning("Exception while calling onDestroyed in behavior" + behavior.getClass().getSimpleName() + " for NPC " + this.getName());
                    }
                }
            }
        } catch (Exception ex) {
            Canary.logWarning("Exception occured while calling destroyed for NPC " + this.getName());
            Canary.logStackTrace("", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityNonPlayableCharacter getHandle() {
        return (EntityNonPlayableCharacter) entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attackEntity(Entity entity) {
        this.lookAt(entity);//looks at the entity
        this.getHandle().bK();// swings the arm
        this.getHandle().q(((CanaryEntity)entity).getHandle());// attacks the target
    }

    /**
     * {@inheritDoc}
     * Needed to make NPC's turn.
     */
    @Override
    public void moveEntity(double x, double y, double z) {
        this.lookAt(x, y, z);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canFly() {
        return this.getHandle().ce.c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFly(boolean bool) {
        this.getHandle().ce.c = bool;
        this.getHandle().ce.b = bool;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("NPC[id=%d, name=%s]", getID(), getName());
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 89 * hash + this.getID();
        hash = 89 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
        return hash;
    }
}
