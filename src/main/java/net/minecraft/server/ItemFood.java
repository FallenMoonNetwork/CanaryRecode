package net.minecraft.server;

import net.canarymod.api.potion.CanaryPotionEffect;
import net.canarymod.hook.player.EatHook;

public class ItemFood extends Item {

    public final int a;
    private final int b;
    private final float c;
    private final boolean d;
    private boolean cB;
    private int cC;
    private int cD;
    private int cE;
    private float cF;

    public ItemFood(int i0, int i1, float f0, boolean flag0) {
        super(i0);
        this.a = 32;
        this.b = i1;
        this.d = flag0;
        this.c = f0;
        this.a(CreativeTabs.h);
    }

    public ItemFood(int i0, int i1, boolean flag0) {
        this(i0, i1, 0.6F, flag0);
    }

    public ItemStack b(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        // CanaryMod: Eat
        net.canarymod.api.potion.PotionEffect[] effects = null;

        if (this instanceof ItemAppleGold && !world.I) {
            if (itemstack.k() > 0) {
                effects = new net.canarymod.api.potion.PotionEffect[]{
                        new CanaryPotionEffect(new PotionEffect(Potion.x.H, 2400, 0)), new CanaryPotionEffect(new PotionEffect(Potion.l.H, 600, 4)), new CanaryPotionEffect(new PotionEffect(Potion.m.H, 6000, 0)), new CanaryPotionEffect(new PotionEffect(Potion.n.H, 6000, 0))
                };
            }
            else {
                effects = new net.canarymod.api.potion.PotionEffect[]{ new CanaryPotionEffect(new PotionEffect(Potion.x.H, 2400, 0)) };
            }
        } else if (!world.I && this.cv > 0 && world.s.nextFloat() < this.cF) {
            effects = new net.canarymod.api.potion.PotionEffect[]{ new CanaryPotionEffect(new PotionEffect(this.cC, this.cD * 20, this.cE)) };
        }
        EatHook hook = (EatHook) new EatHook(((EntityPlayerMP) entityplayer).getPlayer(), itemstack.getCanaryItem(), this.g(), this.i(), effects).call();
        if (!hook.isCanceled()) {
            --itemstack.b;
            entityplayer.bH().a(hook.getLevelGain(), hook.getSaturationGain());
            world.a((Entity) entityplayer, "random.burp", 0.5F, world.s.nextFloat() * 0.1F + 0.9F);
            // this.c(itemstack, world, entityplayer); moved above and below
            if (hook.getPotionEffects() != null) {
                for (net.canarymod.api.potion.PotionEffect effect : hook.getPotionEffects()) {
                    if (effect != null) {
                        entityplayer.c(((CanaryPotionEffect) effect).getHandle());
                    }
                }
            }
        }
        //
        return itemstack;
    }

    protected void c(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (!world.I && this.cC > 0 && world.s.nextFloat() < this.cF) {
            entityplayer.c(new PotionEffect(this.cC, this.cD * 20, this.cE));
        }
    }

    public int d_(ItemStack itemstack) {
        return 32;
    }

    public EnumAction c_(ItemStack itemstack) {
        return EnumAction.b;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.g(this.cB)) {
            entityplayer.a(itemstack, this.d_(itemstack));
        }

        return itemstack;
    }

    public int g() {
        return this.b;
    }

    public float i() {
        return this.c;
    }

    public boolean j() {
        return this.d;
    }

    public ItemFood a(int i0, int i1, int i2, float f0) {
        this.cC = i0;
        this.cD = i1;
        this.cE = i2;
        this.cF = f0;
        return this;
    }

    public ItemFood k() {
        this.cB = true;
        return this;
    }
}
