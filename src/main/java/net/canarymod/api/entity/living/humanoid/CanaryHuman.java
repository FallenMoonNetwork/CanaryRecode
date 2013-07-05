package net.canarymod.api.entity.living.humanoid;

import net.canarymod.Canary;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.GameMode;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.CanaryLivingBase;
import net.canarymod.api.inventory.CanaryPlayerInventory;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.canarymod.config.Configuration;
import net.canarymod.hook.player.TeleportHook;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.EnumGameType;
import net.minecraft.server.ItemStack;
import net.minecraft.server.WorldSettings;

/**
 * Human implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryHuman extends CanaryLivingBase implements Human {
    protected String prefix = null;

    public CanaryHuman(EntityPlayerMP entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return getHandle().c_();
    }

    /**
     * {@inheritDoc}
     */
    public String getDisplayName() {
        return getHandle().getDisplayName();
    }

    /**
     * {@inheritDoc}
     */
    public void setDisplayName(String name) {
        getHandle().setDisplayName(name);
    }

    @Override
    public void kill() {
        super.kill();
        dropInventory();
    }

    public void switchWorlds(World dim) {
        EntityPlayerMP ent = getHandle();

        // Dismount first or get buggy
        if (ent.o != null) {
            ent.h(ent.o);
        }
        // ent.a((StatBase) AchievementList.B);
        Canary.getServer().getConfigurationManager().switchDimension(ent.getPlayer(), dim, false);
        // ((EntityPlayerMP)entity).changeWorld((WorldServer) ((CanaryWorld) dim).getHandle());
        // ((EntityPlayerMP)entity).b.ad().a(((EntityPlayerMP)entity), dim.getName(), dim.getType().getId());
        // refreshCreativeMode();
    }

    @Override
    public EntityItem[] dropInventory() {
        Item[] items = getInventory().getContents();
        EntityItem[] drops = new EntityItem[items.length];

        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                continue;
            }
            drops[i] = getWorld().dropItem(getPosition(), items[i]);

        }
        getInventory().clearContents();
        return drops;
    }

    @Override
    public boolean isDamageDisabled() {
        return getHandle().bG.a;
    }

    @Override
    public void setDamageDisabled(boolean disabled) {
        getHandle().bG.a = disabled;
    }

    @Override
    public int getModeId() {
        return getHandle().c.b().a();
    }

    public GameMode getMode() {
        return GameMode.fromId(getHandle().c.b().a());
    }

    @Override
    public void setModeId(int mode) {
        // Adjust mode, make it null if number is invalid
        EnumGameType gt = WorldSettings.a(mode);
        EntityPlayerMP ent = getHandle();

        if (ent.c.b() != gt) {
            ent.c.a(gt);
            ent.getServerHandler().sendPacket(new CanaryPacket(new net.minecraft.server.Packet70GameEvent(3, mode)));
        }
    }

    public void setMode(GameMode mode) {
        this.setModeId(mode.getId());
    }

    public void refreshCreativeMode() {
        if (getModeId() == 1 || Configuration.getWorldConfig(getWorld().getFqName()).getGameMode() == GameMode.CREATIVE) {
            getHandle().c.a(WorldSettings.a(1));
        } else {
            getHandle().c.a(WorldSettings.a(0));
        }
    }

    @Override
    public boolean isBlocking() {
        return getHandle().bq();
    }

    @Override
    public boolean isInVehicle() {
        return getHandle().o != null;
    }

    @Override
    public void addExhaustion(float exhaustion) {
        getHandle().bD().a(exhaustion);
    }

    @Override
    public void setExhaustion(float exhaustion) {
        getHandle().bD().setExhaustionLevel(exhaustion);
    }

    @Override
    public float getExhaustionLevel() {
        return getHandle().bD().getExhaustionLevel();
    }

    @Override
    public void setHunger(int hunger) {
        getHandle().bD().setFoodLevel(hunger);
    }

    @Override
    public int getHunger() {
        return getHandle().bD().a();
    }

    @Override
    public void addExperience(int experience) {
        getHandle().addXP(experience);
    }

    @Override
    public void removeExperience(int experience) {
        getHandle().removeXP(experience);
    }

    @Override
    public int getExperience() {
        return getHandle().bI;
    }

    @Override
    public void setExperience(int xp) {
        if (xp < 0) {
            return;
        }
        getHandle().setXP(xp);
    }

    @Override
    public int getLevel() {
        return getHandle().bH;
    }

    @Override
    public boolean isSleeping() {
        return getHandle().bd();
    }

    @Override
    public boolean isDeeplySleeping() {
        return getHandle().by();
    }

    @Override
    public void destroyItemHeld() {
        getHandle().bu();
    }

    @Override
    public Item getItemHeld() {
        ItemStack item = ((CanaryPlayerInventory) getInventory()).getItemInHand();

        if (item != null) {
            Item cItem = item.getCanaryItem();
            cItem.setSlot(((CanaryPlayerInventory) getInventory()).getSelectedHotbarSlot());
            return cItem;
        }
        return null;
    }

    @Override
    public void dropItem(Item item) {
        getWorld().dropItem((int) getX(), (int) getY(), (int) getZ(), item);
    }

    public boolean canFly() {
        return getHandle().bG.c;
    }

    @Override
    public boolean isFlying() {
        return getHandle().bG.b;
    }

    @Override
    public void setFlying(boolean flying) {
        getHandle().bG.c = flying;
        getHandle().bG.b = flying;
    }

    @Override
    public Inventory getInventory() {
        return getHandle().getPlayerInventory();
    }

    @Override
    public void giveItem(Item item) {
        getHandle().getPlayerInventory().addItem(item);
        getHandle().getPlayerInventory().update();
    }

    @Override
    public void teleportTo(double x, double y, double z) {
        teleportTo(x, y, z, 0.0F, 0.0F);
    }

    @Override
    public void teleportTo(Position position) {
        teleportTo(position.getX(), position.getY(), position.getZ(), 0.0f, 0.0f);
    }

    @Override
    public void teleportTo(double x, double y, double z, World dim) {
        if (!(getWorld().getType().equals(dim.getType()))) {
            Canary.logDebug("Switching world from " + getWorld().getFqName() + " to " + dim.getFqName());
            switchWorlds(dim);
        }
        teleportTo(x, y, z, 0.0F, 0.0F);
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch, float rotation, World dim) {
        if (!(getWorld().getType().equals(dim.getType()))) {
            Canary.logDebug("Switching world from " + getWorld().getFqName() + " to " + dim.getFqName());
            switchWorlds(dim);
        }
        teleportTo(x, y, z, pitch, rotation);
    }

    @Override
    public void teleportTo(double x, double y, double z, float pitch, float rotation) {
        this.teleportTo(x, y, z, pitch, rotation, TeleportHook.TeleportCause.PLUGIN);
    }

    @Override
    public void teleportTo(Location location) {
        if (getWorld() != location.getWorld()) {
            Canary.logDebug("Switching world from " + getWorld().getFqName() + " to " + location.getWorld().getFqName());
            switchWorlds(location.getWorld());
        }
        teleportTo(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getRotation());
    }

    protected void teleportTo(double x, double y, double z, float pitch, float rotation, TeleportHook.TeleportCause cause) {
        EntityPlayerMP player = (EntityPlayerMP) entity;

        // If player is in vehicle - eject them before they are teleported.
        if (isRiding()) {
            player.h(player.o);
        }

        player.a.a(x, y, z, rotation, pitch, getWorld().getType().getId(), getWorld().getName(), cause);
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public EntityPlayerMP getHandle() {
        return (EntityPlayerMP) entity;
    }
}
