package net.canarymod.api.entity.living;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.DamageSource;
import net.canarymod.api.DamageType;
import net.canarymod.api.PathFinder;
import net.canarymod.api.ai.AIManager;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.entity.living.monster.EntityMob;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.nbt.BaseTag;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.potion.CanaryPotion;
import net.canarymod.api.potion.CanaryPotionEffect;
import net.canarymod.api.potion.Potion;
import net.canarymod.api.potion.PotionEffect;
import net.canarymod.api.potion.PotionEffectType;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.minecraft.server.EntityList;
import net.minecraft.server.IAnimals;
import net.minecraft.server.IMob;
import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.Packet12PlayerLook;
import net.minecraft.server.Packet32EntityLook;


/**
 * Living Entity wrapper implementation
 *
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryEntityLiving extends CanaryEntity implements EntityLiving {

    public CanaryEntityLiving(net.minecraft.server.EntityLiving entity) {
        super(entity);
    }

    @Override
    public boolean canSee(EntityLiving entityliving) {
        return ((net.minecraft.server.EntityLiving) entity).n(((CanaryEntity) entityliving).getHandle());
    }

    @Override
    public void dealDamage(DamageType damagetype, int amount) {
        DamageSource theSource = CanaryDamageSource.getDamageSourceFromType(damagetype);

        if (theSource != null) {
            ((net.minecraft.server.EntityLiving) entity).a(((CanaryDamageSource) theSource).getHandle(), amount);
        }
    }

    @Override
    public int getHealth() {
        return ((net.minecraft.server.EntityLiving) entity).aX();
    }

    @Override
    public int getMaxHealth() {
        return ((net.minecraft.server.EntityLiving) entity).aW();
    }

    @Override
    public void setHealth(int newHealth) {
        ((net.minecraft.server.EntityLiving) entity).b(newHealth);
    }

    @Override
    public void increaseHealth(int increase) {
        ((net.minecraft.server.EntityLiving) entity).j(increase);
    }

    @Override
    public void setMaxHealth(int maxHealth) {
        ((net.minecraft.server.EntityLiving) entity).setMaxHealth(maxHealth);
    }

    @Override
    public int getAge() {
        return ((net.minecraft.server.EntityLiving) entity).aH();
    }

    @Override
    public void setAge(int age) {
        ((net.minecraft.server.EntityLiving) entity).bC = age;
    }

    @Override
    public boolean isAnimal() {
        return this.entity instanceof IAnimals && !(entity instanceof IMob);
    }

    @Override
    public boolean isMob() {
        return entity instanceof IMob;
    }

    @Override
    public boolean isPlayer() {
        return entity.getCanaryEntity() instanceof CanaryPlayer;
    }

    @Override
    public EntityAnimal getAnimal() {
        if (isAnimal()) {
            return (EntityAnimal) this;
        }
        return null;
    }

    @Override
    public EntityMob getMob() {
        if (isMob()) {
            return (EntityMob) this;
        }
        return null; // Not a valid mob type
    }

    @Override
    public Player getPlayer() {
        if (isPlayer()) {
            return (Player) this;
        }
        return null;
    }

    @Override
    public String getName() {
        return EntityList.b(entity);
    }

    @Override
    public void kill() {
        setHealth(0);
    }

    @Override
    public void destroy() {
        entity.w();
    }

    @Override
    public void knockBack(double x, double z) {
        ((net.minecraft.server.EntityLiving) entity).a(entity, 0, x, z);
    }

    @Override
    public void moveEntity(double x, double y, double z) {
        entity.d(x, y, z);
    }

    @Override
    public void playLivingSound() {
        ((net.minecraft.server.EntityLiving) entity).aR();
    }

    @Override
    public boolean hasHome() {
        return ((net.minecraft.server.EntityLiving) entity).aP();
    }

    @Override
    public Location getHome() {
        if (hasHome()) {
            net.minecraft.server.ChunkCoordinates home = ((net.minecraft.server.EntityLiving) entity).aM();

            return new Location(home.a, home.b, home.c);
        }
        return null;
    }

    @Override
    public void removeHome() {
        ((net.minecraft.server.EntityLiving) entity).aO();
    }

    @Override
    public void setHome(Location location) {
        setHomeArea((int) Math.floor(location.getX()), (int) Math.floor(location.getY()), (int) Math.floor(location.getZ()), 25);
    }

    @Override
    public void setHomeArea(Position vector, int dist) {
        if (vector == null) {
            throw new IllegalArgumentException("Could not set EntityLiving's home. Location was null!");
        }
        setHomeArea((int) Math.floor(vector.getX()), (int) Math.floor(vector.getY()), (int) Math.floor(vector.getZ()), dist);
    }

    @Override
    public void setHomeArea(int x, int y, int z, int dist) {
        ((net.minecraft.server.EntityLiving) entity).b(x, y, z, dist);
    }

    @Override
    public void setHomeRadius(int newRadius) {
        setHomeArea(getHome(), newRadius);
    }

    @Override
    public boolean spawn(EntityLiving... riders) {
        net.minecraft.server.World world = ((CanaryWorld) getWorld()).getHandle();

        entity.b(getX() + 0.5d, getY(), getZ() + 0.5d, getRotation(), 0f);
        boolean toRet = world.d(entity);

        if (riders != null) {
            CanaryEntityLiving prev = this;

            for (EntityLiving rider : riders) {
                net.minecraft.server.EntityLiving mob2 = (net.minecraft.server.EntityLiving) ((CanaryEntityLiving) rider).getHandle();

                mob2.b(getX(), getY(), getZ(), getRotation(), 0f);
                world.d(mob2);
                mob2.a(prev.getHandle());
            }
        }
        return toRet;

    }

    @Override
    public void addPotionEffect(PotionEffect effect) {
        net.minecraft.server.PotionEffect oEffect = ((CanaryPotionEffect) effect).getHandle();

        ((net.minecraft.server.EntityLiving) entity).d(oEffect);
    }

    @Override
    public void addPotionEffect(PotionEffectType type, int duration, int amplifier) {
        net.minecraft.server.PotionEffect oEffect = new net.minecraft.server.PotionEffect(type.getID(), duration, amplifier);

        ((net.minecraft.server.EntityLiving) entity).d(oEffect);
    }

    @Override
    public boolean isPotionActive(Potion potion) {
        net.minecraft.server.Potion oPotion = ((CanaryPotion) potion).getHandle();

        return ((net.minecraft.server.EntityLiving) entity).a(oPotion);
    }

    @Override
    public PotionEffect getActivePotionEffect(Potion potion) {
        net.minecraft.server.Potion oPotion = ((CanaryPotion) potion).getHandle();
        net.minecraft.server.PotionEffect oPotionEffect = ((net.minecraft.server.EntityLiving) entity).b(oPotion);

        return new CanaryPotionEffect(oPotionEffect);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PotionEffect> getAllActivePotionEffects() {
        Collection<net.minecraft.server.PotionEffect> collection = ((net.minecraft.server.EntityLiving) entity).bC();
        List<PotionEffect> list = new ArrayList<PotionEffect>();

        for (net.minecraft.server.PotionEffect oEffect : collection) {
            list.add(new CanaryPotionEffect(oEffect));
        }
        return list;
    }

    @Override
    public int getDeathTicks() {
        return ((net.minecraft.server.EntityLiving) entity).aZ;
    }

    @Override
    public void setDeathTicks(int ticks) {
        ((net.minecraft.server.EntityLiving) entity).aZ = ticks;
    }

    @Override
    public int getInvulnerabilityTicks() {
        return ((net.minecraft.server.EntityLiving) entity).av;
    }

    @Override
    public void setInvulnerabilityTicks(int ticks) {
        ((net.minecraft.server.EntityLiving) entity).av = ticks;
    }

    @Override
    public EntityLiving getTarget() {
        net.minecraft.server.Entity target = ((net.minecraft.server.EntityLiving) entity).aJ();

        if (target != null) {
            return (EntityLiving) ((net.minecraft.server.EntityLiving) target).getCanaryEntity();
        }
        return null;
    }

    @Override
    public void setTarget(EntityLiving entityliving) {
        if (entityliving == null) {
            ((net.minecraft.server.EntityLiving) entity).b((net.minecraft.server.EntityLiving) null);
        } else {
            ((net.minecraft.server.EntityLiving) entity).b((net.minecraft.server.EntityLiving) ((CanaryEntity) entityliving).getHandle());
        }
    }

    @Override
    public void lookAt(double x, double y, double z) {

        double xDiff = x - getX();
        double yDiff = y - getY();
        double zDiff = z - getZ();
        double DistanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        double DistanceY = Math.sqrt(DistanceXZ * DistanceXZ + yDiff * yDiff);
        double yaw = (Math.acos(xDiff / DistanceXZ) * 180 / Math.PI);
        double pitch = (Math.acos(yDiff / DistanceY) * 180 / Math.PI) - 90;

        if (zDiff < 0.0) {
            yaw = yaw + (Math.abs(180 - yaw) * 2);
        }

        setRotation((float) yaw);
        setPitch((float) pitch);

        net.minecraft.server.Packet toSend;

        if (isPlayer()) {
            toSend = new Packet12PlayerLook();
            Packet12PlayerLook toSend2 = (Packet12PlayerLook) toSend;

            toSend2.e = (float) yaw;
            toSend2.f = (float) pitch;
            getPlayer().sendPacket(new CanaryPacket(toSend));
        } else {
            double rotation2 = Math.floor((yaw * 256F) / 360F);
            double pitch2 = Math.floor((pitch * 256F) / 360F);

            toSend = new Packet32EntityLook(getID(), (byte) rotation2, (byte) pitch2);
            Canary.getServer().getConfigurationManager().sendPacketToAllInWorld(getWorld().getName(), new CanaryPacket(toSend));
        }
    }

    @Override
    public void lookAt(Location location) {
        lookAt(location.getX(), location.getY(), location.getZ());
    }

    @Override
    public Item getItemInHand() {
        return new CanaryItem(((net.minecraft.server.EntityLiving) entity).bG());
    }

    @Override
    public Item[] getEquipment() {
        Item[] stack = new Item[5];
        ItemStack[] istack = ((net.minecraft.server.EntityLiving) entity).ad();

        for (int i = 0; i < 5; i++) {
            if (istack[i] != null) {
                stack[i] = new CanaryItem(istack[i]);
            }
        }
        return stack;
    }

    @Override
    public Item getEquipmentInSlot(int slot) {
        ItemStack istack = ((net.minecraft.server.EntityLiving) entity).p(slot);

        if (istack != null) {
            return new CanaryItem(istack);
        }
        return null;
    }

    @Override
    public void setEquipment(Item[] items) {
        for (int i = 0; i < 5; i++) {
            if (items[i] == null) {
                continue;
            }
            ((net.minecraft.server.EntityLiving) entity).c(i, ((CanaryItem) items[i]).getHandle());
        }
    }

    @Override
    public void setEquipment(Item item, int slot) {
        if (slot >= 5) {
            return; // TODO: Response for user...
        }
        ((net.minecraft.server.EntityLiving) entity).c(slot, ((CanaryItem) item).getHandle());
    }

    @Override
    public float getDropChance(int slot) {
        return ((net.minecraft.server.EntityLiving) entity).getDropChance(slot);
    }

    @Override
    public void setDropChance(int slot, float chance) {
        ((net.minecraft.server.EntityLiving) entity).a(slot, chance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PathFinder getPathFinder() {
        return ((net.minecraft.server.EntityLiving) entity).getPathNavigator().getCanaryPathFinder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveEntityToXYZ(double x, double y, double z, float speed) {
        this.lookAt(x, y, z);
        ((net.minecraft.server.EntityLiving) entity).aA().a(x, y, z, speed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AIManager getAITaskManager() {
        return ((net.minecraft.server.EntityLiving) entity).getAITasks().getAIManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getArrowCountInEntity() {
        return ((net.minecraft.server.EntityLiving) entity).bM();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArrowCountInEntity(int arrows) {
        ((net.minecraft.server.EntityLiving) entity).r(arrows);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attackEntity(Entity entity, int damage){
        if (!(entity instanceof net.canarymod.api.entity.living.EntityLiving)) {
            return;
        }
        ((net.minecraft.server.EntityLiving)this.getHandle()).bK();// swings the arm
        ((net.canarymod.api.entity.living.CanaryEntityLiving)entity).getHandle().a(net.minecraft.server.DamageSource.j, damage);
    }
}
