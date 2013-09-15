package net.minecraft.server;

import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.DamageType;
import net.canarymod.api.entity.hanging.CanaryItemFrame;
import net.canarymod.api.entity.hanging.HangingEntity;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.HangingEntityDestroyHook;
import net.canarymod.logger.Logman;

public class EntityItemFrame extends EntityHanging {

    public float e = 1.0F; // CanaryMod: private -> public

    public EntityItemFrame(World world) {
        super(world);
        this.entity = new CanaryItemFrame(this); // CanaryMod: Wrap Entity
    }

    public EntityItemFrame(World world, int i0, int i1, int i2, int i3) {
        super(world, i0, i1, i2, i3);
        this.a(i3);
        this.entity = new CanaryItemFrame(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        this.u().a(2, 5);
        this.u().a(3, Byte.valueOf((byte) 0));
    }

    public int d() {
        return 9;
    }

    public int e() {
        return 9;
    }

    public void b(Entity entity) {
        //CanaryMod start
        HangingEntityDestroyHook hook = null;
        boolean isPlayer = false;
        if(entity instanceof EntityPlayer) {
            isPlayer = true;
            hook = (HangingEntityDestroyHook) new HangingEntityDestroyHook((HangingEntity) this.getCanaryEntity(), (Player)entity.getCanaryEntity(), CanaryDamageSource.getDamageSourceFromType(DamageType.GENERIC)).call();
        }
        else {
            hook = (HangingEntityDestroyHook) new HangingEntityDestroyHook((HangingEntity) this.getCanaryEntity(), null, CanaryDamageSource.getDamageSourceFromType(DamageType.GENERIC)).call();
        }
        if(hook.isCanceled()) {
            return;
        }
        //CanaryMod end
        
        ItemStack itemstack = this.h();
        //CanaryMod: Changed to spare an instanceof
        if (isPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entity;

            if (entityplayer.bG.d) {
                this.b(itemstack);
                return;
            }
        }

        this.a(new ItemStack(Item.bK), 0.0F);
        if (itemstack != null && this.ab.nextFloat() < this.e) {
            itemstack = itemstack.m();
            this.b(itemstack);
            this.a(itemstack, 0.0F);
        }
    }

    private void b(ItemStack itemstack) {
        if (itemstack != null) {
            if (itemstack.d == Item.bf.cv) {
                MapData mapdata = ((ItemMap) itemstack.b()).a(itemstack, this.q);

                mapdata.g.remove("frame-" + this.k);
            }

            itemstack.a((EntityItemFrame) null);
        }
    }

    public ItemStack h() {
        return this.u().f(2);
    }

    public void a(ItemStack itemstack) {
        itemstack = itemstack.m();
        itemstack.b = 1;
        itemstack.a(this);
        this.u().b(2, itemstack);
        this.u().h(2);
    }

    public int i() {
        return this.u().a(3);
    }

    public void c(int i0) {
        this.u().b(3, Byte.valueOf((byte) (i0 % 4)));
    }

    public void b(NBTTagCompound nbttagcompound) {
        if (this.h() != null) {
            nbttagcompound.a("Item", this.h().b(new NBTTagCompound()));
            nbttagcompound.a("ItemRotation", (byte) this.i());
            nbttagcompound.a("ItemDropChance", this.e);
        }

        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = nbttagcompound.l("Item");

        if (nbttagcompound1 != null && !nbttagcompound1.d()) {
            this.a(ItemStack.a(nbttagcompound1));
            this.c(nbttagcompound.c("ItemRotation"));
            if (nbttagcompound.b("ItemDropChance")) {
                this.e = nbttagcompound.g("ItemDropChance");
            }
        }

        super.a(nbttagcompound);
    }

    public boolean c(EntityPlayer entityplayer) {
        if (this.h() == null) {
            ItemStack itemstack = entityplayer.aY();

            if (itemstack != null && !this.q.I) {
                this.a(itemstack);
                if (!entityplayer.bG.d && --itemstack.b <= 0) {
                    entityplayer.bn.a(entityplayer.bn.c, (ItemStack) null);
                }
            }
        } else if (!this.q.I) {
            this.c(this.i() + 1);
        }

        return true;
    }
}
