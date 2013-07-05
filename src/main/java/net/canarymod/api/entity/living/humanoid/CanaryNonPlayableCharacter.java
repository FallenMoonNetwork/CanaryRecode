package net.canarymod.api.entity.living.humanoid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityType;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.Colors;
import net.minecraft.server.Packet20NamedEntitySpawn;
import net.minecraft.server.Packet29DestroyEntity;

/**
 * NonPlayableCharacter implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryNonPlayableCharacter extends CanaryHuman implements NonPlayableCharacter {
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
    public EntityType getEntityType() {
        return EntityType.NPC;
    }

    @Override
    public String getFqName() {
        return "NonPlayableCharacter";
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
        player.message("(MSG) " + prefix.replace("%name", getName()) + msg);
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
                        Canary.logStacktrace("", ex);
                    }
                }
            }
        } catch (Exception ex) {
            Canary.logWarning("Exception occured while calling update for NPC " + this.getName());
            Canary.logStacktrace("", ex);
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
                        Canary.logStacktrace("", ex);
                    }
                }
            }
        } catch (Exception ex) {
            Canary.logWarning("Exception while calling clicked for NPC " + this.getName());
            Canary.logStacktrace("", ex);
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
            Canary.logStacktrace("", ex);
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
            Canary.logStacktrace("", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attackEntity(Entity entity) {
        this.lookAt(entity);// looks at the entity
        this.swingArm();
        this.getHandle().q(((CanaryEntity) entity).getHandle());// attacks the target XXX
    }

    /**
     * {@inheritDoc} Needed to make NPC's turn.
     */
    @Override
    public void moveEntity(double x, double y, double z) {
        this.lookAt(x, y, z);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityNonPlayableCharacter getHandle() {
        return (EntityNonPlayableCharacter) entity;
    }
}
