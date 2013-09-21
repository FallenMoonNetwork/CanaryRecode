package net.canarymod.api.entity.living.humanoid;

import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.living.humanoid.npchelpers.EntityNPCJumpHelper;
import net.canarymod.api.entity.living.humanoid.npchelpers.EntityNPCMoveHelper;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.position.Location;
import net.minecraft.server.ChatMessageComponent;
import net.minecraft.server.ChunkCoordinates;
import net.minecraft.server.DamageSource;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.World;

/**
 * NonPlayableCharacter (NPC) Entity class
 *
 * @author Jason (darkdiplomat)
 */
public final class EntityNonPlayableCharacter extends EntityPlayer {
    private EntityNPCMoveHelper move_helper;
    private EntityNPCJumpHelper jump_helper;

    public EntityNonPlayableCharacter(String name, Location location) {
        super(((CanaryWorld) location.getWorld()).getHandle(), name);
        World world = ((CanaryWorld) location.getWorld()).getHandle();
        this.move_helper = new EntityNPCMoveHelper(this);
        this.jump_helper = new EntityNPCJumpHelper(this);
        this.Y = 0.0F;
        this.N = 0.0F;
        this.b((double) location.getX(), location.getY(), location.getZ(), location.getRotation(), location.getPitch());

        while (!world.a((Entity) this, this.E).isEmpty()) {
            this.b(this.u, this.v + 1.0D, this.w);
        }
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
        super.l_();
        if (!this.M) {
            getNPC().update();
        }
    }

    @Override
    public boolean a(EntityPlayer entityplayer) { // RightClicked
        if (this.entity != null) {
            getNPC().clicked(((EntityPlayerMP) entityplayer).getPlayer());
            return true;
        }
        return false;
    }

    @Override
    public boolean a(DamageSource damagesource, float i0) {
        boolean toRet = super.a(damagesource, i0);

        if (toRet && damagesource.i() != null) {
            CanaryEntity atk = damagesource.i().getCanaryEntity();
            getNPC().attacked(atk);
        }
        return toRet;
    }

    @Override
    public void x() {
        super.x();
        getNPC().destroyed();
    }

    protected void bi() {
        this.move_helper.c();
    }

    public void entityliving_n_clone(float f0) {
        this.bf = f0;
    }

    public EntityNPCJumpHelper getJumpHelper() {
        return jump_helper;
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
        return (CanaryNonPlayableCharacter) entity;
    }
}
