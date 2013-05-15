package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.potion.CanaryPotionEffect;
import net.canarymod.hook.player.EatHook;


public class ItemFood extends Item {

    public final int a;
    private final int b;
    private final float c;
    private final boolean d;
    private boolean cu;
    private int cv;
    private int cw;
    private int cx;
    private float cy;

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

        if (this instanceof ItemAppleGold && !world.I && itemstack.k() > 0) {
            effects = new net.canarymod.api.potion.PotionEffect[] {
                new CanaryPotionEffect(new PotionEffect(Potion.l.H, 600, 3)), new CanaryPotionEffect(new PotionEffect(Potion.m.H, 6000, 0)), new CanaryPotionEffect(new PotionEffect(Potion.n.H, 6000, 0))
            };
        } else if (!world.I && this.cv > 0 && world.s.nextFloat() < this.cy) {
            effects = new net.canarymod.api.potion.PotionEffect[] { new CanaryPotionEffect(new PotionEffect(this.cv, this.cw * 20, this.cx)) };
        }
        EatHook hook = new EatHook(((EntityPlayerMP) entityplayer).getPlayer(), itemstack.getCanaryItem(), this.g(), this.h(), effects);

        Canary.hooks().callHook(hook);
        if (!hook.isCanceled()) {
            --itemstack.a;
            entityplayer.cn().a(hook.getLevelGain(), hook.getSaturationGain());
            world.a((Entity) entityplayer, "random.burp", 0.5F, world.s.nextFloat() * 0.1F + 0.9F);
            // this.c(itemstack, world, entityplayer); moved above and below
            if (hook.getPotionEffects() != null) {
                for (net.canarymod.api.potion.PotionEffect effect : hook.getPotionEffects()) {
                    if (effect != null) {
                        entityplayer.d(((CanaryPotionEffect) effect).getHandle());
                    }
                }
            }
        }
        //
        return itemstack;
    }

    protected void c(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (!world.I && this.cv > 0 && world.s.nextFloat() < this.cy) {
            entityplayer.d(new PotionEffect(this.cv, this.cw * 20, this.cx));
        }
    }

    public int c_(ItemStack itemstack) {
        return 32;
    }

    public EnumAction b_(ItemStack itemstack) {
        return EnumAction.b;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.i(this.cu)) {
            entityplayer.a(itemstack, this.c_(itemstack));
        }

        return itemstack;
    }

    public int g() {
        return this.b;
    }

    public float h() {
        return this.c;
    }

    public boolean i() {
        return this.d;
    }

    public ItemFood a(int i0, int i1, int i2, float f0) {
        this.cv = i0;
        this.cw = i1;
        this.cx = i2;
        this.cy = f0;
        return this;
    }

    public ItemFood j() {
        this.cu = true;
        return this;
    }
}
