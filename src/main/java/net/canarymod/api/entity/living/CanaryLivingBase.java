package net.canarymod.api.entity.living;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.DamageSource;
import net.canarymod.api.DamageType;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.potion.CanaryPotion;
import net.canarymod.api.potion.CanaryPotionEffect;
import net.canarymod.api.potion.Potion;
import net.canarymod.api.potion.PotionEffect;
import net.canarymod.api.potion.PotionEffectType;
import net.canarymod.api.world.position.Location;
import net.minecraft.server.EntityLivingBase;
import net.minecraft.server.Packet12PlayerLook;
import net.minecraft.server.Packet32EntityLook;

public abstract class CanaryLivingBase extends CanaryEntity implements LivingBase {

    public CanaryLivingBase(EntityLivingBase entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getHealth() {
        return getHandle().aM();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHealth(float newHealth) {
        getHandle().g(newHealth);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseHealth(float increase) {
        getHandle().f(increase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canSee(LivingBase livingbase) {
        return getHandle().o(((CanaryEntity) livingbase).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDeathTicks() {
        return getHandle().aB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDeathTicks(int ticks) {
        getHandle().aB = ticks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInvulnerabilityTicks() {
        return getHandle().aI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInvulnerabilityTicks(int ticks) {
        getHandle().aI = ticks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAge() {
        return getHandle().aE();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAge(int age) {
        getHandle().setAge(age);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void kill() {
        setHealth(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dealDamage(DamageType damagetype, float amount) {
        DamageSource theSource = CanaryDamageSource.getDamageSourceFromType(damagetype);
        if (theSource != null) {
            getHandle().a(((CanaryDamageSource) theSource).getHandle(), amount);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void knockBack(double x, double z) {
        getHandle().a(entity, 0, x, z);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPotionEffect(PotionEffect effect) {
        if (effect == null) {
            return;
        }
        getHandle().c(((CanaryPotionEffect) effect).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPotionEffect(PotionEffectType type, int duration, int amplifier) {
        getHandle().c(new net.minecraft.server.PotionEffect(type.getID(), duration, amplifier));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPotionActive(Potion potion) {
        if (potion == null) {
            return false;
        }
        return getHandle().a(((CanaryPotion) potion).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PotionEffect getActivePotionEffect(Potion potion) {
        if (potion == null) {
            return null;
        }
        net.minecraft.server.PotionEffect nms_potioneffect = ((net.minecraft.server.EntityLivingBase) entity).b(((CanaryPotion) potion).getHandle());
        return nms_potioneffect != null ? new CanaryPotionEffect(nms_potioneffect) : null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PotionEffect> getAllActivePotionEffects() {
        Collection<net.minecraft.server.PotionEffect> effect_collection = ((net.minecraft.server.EntityLivingBase) entity).aK();
        List<PotionEffect> list = new ArrayList<PotionEffect>();

        for (net.minecraft.server.PotionEffect nms_effect : effect_collection) {
            list.add(new CanaryPotionEffect(nms_effect));
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LivingBase getRevengeTarget() {
        net.minecraft.server.EntityLivingBase target = getHandle().aD();
        if (target != null) {
            return (LivingBase) target.getCanaryEntity();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRevengeTarget(LivingBase livingbase) {
        if (livingbase == null) {
            getHandle().b((net.minecraft.server.EntityLivingBase) null);
        } else {
            getHandle().b(((CanaryLivingBase) livingbase).getHandle());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LivingBase getLastAssailant() {
        net.minecraft.server.EntityLivingBase target = ((net.minecraft.server.EntityLivingBase) entity).aD();
        if (target != null) {
            return (EntityLiving) target.getCanaryEntity();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastAssailant(LivingBase livingbase) {
        if (livingbase == null) {
            getHandle().k((net.minecraft.server.Entity) null);
        } else {
            getHandle().k(((CanaryEntity) livingbase).getHandle());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lookAt(double x, double y, double z) {
        double xDiff = x - getX();
        double yDiff = y - getY();
        double zDiff = z - getZ();
        double DistanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        double DistanceY = Math.sqrt(DistanceXZ * DistanceXZ + yDiff * yDiff);
        double yaw = (Math.acos(xDiff / DistanceXZ) * 180 / Math.PI);
        double pitch = (Math.acos(yDiff / DistanceY) * 180 - 90 / Math.PI);

        if (zDiff < 0.0) {
            yaw += (Math.abs(180 - yaw) * 2);
        }

        setRotation((float) yaw);
        setPitch((float) pitch);

        net.minecraft.server.Packet toSend;

        if (isPlayer()) {
            toSend = new Packet12PlayerLook();
            Packet12PlayerLook toSend2 = (Packet12PlayerLook) toSend;

            toSend2.e = (float) yaw;
            toSend2.f = (float) pitch;
            ((Player) this.entity).sendPacket(new CanaryPacket(toSend));
        } else {
            double rotation2 = Math.floor((yaw * 256F) / 360F);
            double pitch2 = Math.floor((pitch * 256F) / 360F);

            toSend = new Packet32EntityLook(getID(), (byte) rotation2, (byte) pitch2);
            Canary.getServer().getConfigurationManager().sendPacketToAllInWorld(getWorld().getName(), new CanaryPacket(toSend));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lookAt(Location location) {
        lookAt(location.getX(), location.getY(), location.getZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void lookAt(Entity entity) {
        lookAt(entity.getX(), entity.getY(), entity.getZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getArrowCountInEntity() {
        return getHandle().aT();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArrowCountInEntity(int arrows) {
        getHandle().m(arrows);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void swingArm() {
        getHandle().aR();
    }

    @Override
    public void attackEntity(LivingBase entity, float damage) {
        if (entity == null) {
            return;
        }
        swingArm();
        ((net.canarymod.api.entity.living.CanaryLivingBase) entity).getHandle().a(new net.minecraft.server.EntityDamageSource(getName(), getHandle()), damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityLivingBase getHandle() {
        return (EntityLivingBase) entity;
    }
}
