package net.canarymod.api.entity.living;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.Logman;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.CanaryPacket;
import net.canarymod.api.DamageSource;
import net.canarymod.api.DamageType;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.entity.living.monster.EntityMob;
import net.canarymod.api.entity.potion.CanaryPotion;
import net.canarymod.api.entity.potion.CanaryPotionEffect;
import net.canarymod.api.entity.potion.Potion;
import net.canarymod.api.entity.potion.PotionEffect;
import net.canarymod.api.entity.potion.PotionType;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.minecraft.server.EntityList;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.IAnimals;
import net.minecraft.server.IMob;

/**
 * Living Entity wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryEntityLiving extends CanaryEntity implements EntityLiving{

    public CanaryEntityLiving(net.minecraft.server.EntityLiving entity){
        super(entity);
    }

    @Override
    public boolean canSee(EntityLiving entityliving){
        return ((net.minecraft.server.EntityLiving) entity).n(((CanaryEntity) entityliving).getHandle());
    }

    @Override
    public void dealDamage(DamageType damagetype, int amount){
        DamageSource theSource = CanaryDamageSource.getDamageSourceFromType(damagetype);
        if (theSource != null) {
            ((net.minecraft.server.EntityLiving) entity).a(((CanaryDamageSource) theSource).getHandle(), amount);
        }
    }

    @Override
    public int getAge(){
        return ((net.minecraft.server.EntityLiving) entity).aH();
    }

    @Override
    public boolean isAnimal(){
        return entity instanceof IAnimals && !(entity instanceof IMob);
    }

    @Override
    public boolean isMob(){
        return entity instanceof IMob;
    }

    @Override
    public boolean isPlayer(){
        return entity instanceof EntityPlayerMP;
    }

    @Override
    public EntityAnimal getAnimal(){
        if (isAnimal()) {
            return (EntityAnimal) this;
        }
        return null;
    }

    @Override
    public EntityMob getMob(){
        if (isMob()) {
            return (EntityMob) this;
        }
        return null; // Not a valid mob type
    }

    @Override
    public Player getPlayer(){
        if (isPlayer()) {
            return (Player) this;
        }
        return null;
    }

    @Override
    public int getDeathTicks(){
        return ((net.minecraft.server.EntityLiving) entity).aZ;
    }

    @Override
    public int getHealth(){
        return ((net.minecraft.server.EntityLiving) entity).aX();
    }

    @Override
    public Location getHome(){
        if (hasHome()) {
            net.minecraft.server.ChunkCoordinates home = ((net.minecraft.server.EntityLiving) entity).aM();
            return new Location(home.a, home.b, home.c);
        }
        return null;
    }

    @Override
    public int getMaxHealth(){
        return ((net.minecraft.server.EntityLiving) entity).aW();
    }

    @Override
    public String getName(){
        return EntityList.b(entity);
    }

    @Override
    public boolean hasHome(){
        return ((net.minecraft.server.EntityLiving) entity).aP();
    }

    @Override
    public void increaseHealth(int increase){
        ((net.minecraft.server.EntityLiving) entity).j(increase);
    }

    @Override
    public void kill(){
        setHealth(0);
    }

    @Override
    public void destroy(){
        entity.w();
    }

    @Override
    public void knockBack(double x, double z){
        ((net.minecraft.server.EntityLiving) entity).a(entity, 0, x, z);
    }

    @Override
    public void moveEntity(double x, double y, double z){
        entity.a(x, y, z);
    }

    @Override
    public void playLivingSound(){
        ((OEntityLiving) entity).az();
    }

    @Override
    public void removeHome(){
        ((OEntityLiving) entity).ax();
    }

    @Override
    public void setAge(int age){
        ((OEntityLiving) entity).aV = age;
    }

    @Override
    public void setDeathTicks(int ticks){
        ((OEntityLiving) entity).av = ticks;
    }

    @Override
    public void setHealth(int newHealth){
        ((OEntityLiving) entity).h(newHealth);
    }

    @Override
    public void setHome(Location location){
        setHomeArea((int) Math.floor(location.getX()), (int) Math.floor(location.getY()), (int) Math.floor(location.getZ()), 25);
    }

    @Override
    public void setHomeArea(Position vector, int dist){
        if (vector == null) {
            throw new IllegalArgumentException("Could not set EntityLivings home. Location was null!");
        }
        setHomeArea((int) Math.floor(vector.getX()), (int) Math.floor(vector.getY()), (int) Math.floor(vector.getZ()), dist);
    }

    @Override
    public void setHomeArea(int x, int y, int z, int dist){
        ((OEntityLiving) entity).b(x, y, z, dist);
    }

    @Override
    public void setHomeRadius(int newRadius){
        setHomeArea(getHome(), newRadius);
    }

    @Override
    public void spawn(){
        spawn(null);
    }

    @Override
    public void spawn(EntityLiving rider){
        OWorld world = ((CanaryWorld) getWorld()).getHandle();

        entity.c(getX() + 0.5d, getY(), getZ() + 0.5d, getRotation(), 0f);
        world.b(entity);

        if (rider != null) {
            OEntityLiving mob2 = (OEntityLiving) ((CanaryEntityLiving) rider).getHandle();

            mob2.c(getX(), getY(), getZ(), getRotation(), 0f);
            world.b(mob2);
            mob2.b(entity);
        }

    }

    @Override
    public void addPotionEffect(PotionEffect effect){
        OPotionEffect oEffect = ((CanaryPotionEffect) effect).getHandle();
        ((OEntityLiving) entity).e(oEffect);
    }

    @Override
    public void addPotionEffect(PotionType type, int duration, int amplifier){
        OPotionEffect oEffect = new OPotionEffect(type.getID(), duration, amplifier);
        ((OEntityLiving) entity).e(oEffect);
    }

    @Override
    public boolean isPotionActive(Potion potion){
        OPotion oPotion = ((CanaryPotion) potion).getHandle();
        return ((OEntityLiving) entity).a(oPotion);
    }

    @Override
    public PotionEffect getActivePotionEffect(Potion potion){
        OPotion oPotion = ((CanaryPotion) potion).getHandle();
        OPotionEffect oPotionEffect = ((OEntityLiving) entity).b(oPotion);
        return new CanaryPotionEffect(oPotionEffect);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PotionEffect> getAllActivePotionEffects(){
        Collection<OPotionEffect> collection = ((OEntityLiving) entity).aM();
        List<PotionEffect> list = new ArrayList<PotionEffect>();
        for (OPotionEffect oEffect : collection) {
            list.add(new CanaryPotionEffect(oEffect));
        }
        return list;
    }

    @Override
    public void lookAt(double x, double y, double z){
        double xDiff = x - getX();
        double yDiff = y - getY();
        double zDiff = z - getZ();

        double pitch = -Math.atan2(yDiff, Math.sqrt(Math.pow(xDiff, 2) + Math.pow(zDiff, 2)));
        double rotation = Math.atan2(-xDiff, zDiff);
        Logman.logInfo("Rotation: " + rotation);
        Logman.logInfo("Pitch: " + pitch);
        pitch = Math.toDegrees(pitch);
        rotation = Math.toDegrees(rotation);
        Logman.logInfo("Rotation2: " + rotation);
        Logman.logInfo("Pitch2: " + pitch);

        setRotation((float) rotation);
        setPitch((float) pitch);

        OPacket toSend;
        if (isPlayer()) {
            toSend = new OPacket12PlayerLook();
            OPacket12PlayerLook toSend2 = (OPacket12PlayerLook) toSend;
            toSend2.e = (float) rotation;
            toSend2.f = (float) pitch;
            getPlayer().sendPacket(new CanaryPacket(toSend));
        } else {
            double rotation2 = Math.floor((rotation * 256F) / 360F);
            double pitch2 = Math.floor((pitch * 256F) / 360F);
            toSend = new OPacket32EntityLook(entity.bd, (byte) rotation2, (byte) pitch2);
            Canary.getServer().getConfigurationManager().sendPacketToAllInWorld(getWorld().getName(), new CanaryPacket(toSend));
        }
    }

    @Override
    public void lookAt(Location location){
        lookAt(location.getX(), location.getY(), location.getZ());
    }
}
