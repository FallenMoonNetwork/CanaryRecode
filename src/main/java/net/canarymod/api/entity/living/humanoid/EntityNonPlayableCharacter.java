package net.canarymod.api.entity.living.humanoid;

import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.position.Location;
import net.minecraft.server.ChatMessageComponent;
import net.minecraft.server.ChunkCoordinates;
import net.minecraft.server.DamageSource;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.NBTTagCompound;

public final class EntityNonPlayableCharacter extends EntityPlayer {
    protected boolean isJumping;

    public EntityNonPlayableCharacter(String name, Location location) {
        super(((CanaryWorld) location.getWorld()).getHandle(), name);
        this.a(location.getX(), location.getY(), location.getZ(), location.getRotation(), location.getPitch());
        this.entity = new CanaryNonPlayableCharacter(this);
    }

    @Override
    public void b(int i0) {} // NO PORTAL USE

    @Override
    public void e(NBTTagCompound nbttagcompound) {} // NO NBTTag yet

    @Override
    public void f(NBTTagCompound nbttagcompound) {} // NO NBTTag yet

    @Override
    public void a(NBTTagCompound nbttagcompound) {} // NO NBTTag yet

    @Override
    public void b(NBTTagCompound nbttagcompound) {} // NO NBTTag yet

    @Override
    public boolean c(NBTTagCompound nbttagcompound) { // NO NBTTag yet
        return true;
    }

    @Override
    public boolean d(NBTTagCompound nbttagcompound) { // NO NBTTag yet
        return true;
    }

    @Override
    public void l_() {
        super.c();
        super.l_();
        if (!this.M) {
            try {
                getNPC().update();
            } catch (Exception ex) {// For some reason an exception gets thrown after death and nulled CanaryEntity...
            }
        }
    }

    @Override
    public boolean a(EntityPlayer entityplayer) { // RightClicked
        if (this.entity != null) {
            ((CanaryNonPlayableCharacter) entity).clicked(((EntityPlayerMP) entityplayer).getPlayer());
            return true;
        }
        return false;
    }

    @Override
    public boolean a(DamageSource damagesource, float i0) {
        boolean toRet = super.a(damagesource, i0);

        if (toRet && this.entity != null && damagesource.i() != null) {
            CanaryEntity atk = damagesource.i().getCanaryEntity();

            getNPC().attacked(atk);
        }
        return toRet;
    }

    @Override
    public void w() {
        super.w();
        getNPC().destroyed();
    }

    public void a(float f0) {} // Food Exhaustion stuff

    // ICommandSender things
    @Override
    public void a(ChatMessageComponent chatmessagecomponent) {}

    @Override
    public boolean a(int i0, String s0) {
        return false;
    }

    @Override
    public ChunkCoordinates b() {
        return null;
    }

    //

    public CanaryNonPlayableCharacter getNPC() {
        return (CanaryNonPlayableCharacter) entity; // Not a Player now are we
    }
}
