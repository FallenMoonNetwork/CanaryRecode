package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.player.BlockDestroyHook;
import net.canarymod.hook.player.BlockPlaceHook;


public class ItemBucket extends Item {

    private int a;

    public ItemBucket(int i0, int i1) {
        super(i0);
        this.cw = 1;
        this.a = i1;
        this.a(CreativeTabs.f);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        boolean flag0 = this.a == 0;
        MovingObjectPosition movingobjectposition = this.a(world, entityplayer, flag0);

        if (movingobjectposition == null) {
            return itemstack;
        } else {
            if (movingobjectposition.a == EnumMovingObjectType.a) {
                int i0 = movingobjectposition.b;
                int i1 = movingobjectposition.c;
                int i2 = movingobjectposition.d;

                if (!world.a(entityplayer, i0, i1, i2)) {
                    return itemstack;
                }

                // CanaryMod: BlockDestoryHook
                CanaryBlock clicked = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);
                BlockDestroyHook hook = new BlockDestroyHook(((EntityPlayerMP) entityplayer).getPlayer(), clicked);

                //

                if (this.a == 0) {
                    if (!entityplayer.a(i0, i1, i2, movingobjectposition.e, itemstack)) {
                        return itemstack;
                    }

                    if (world.g(i0, i1, i2) == Material.h && world.h(i0, i1, i2) == 0) {
                        world.i(i0, i1, i2);
                        if (entityplayer.bG.d) {
                            return itemstack;
                        }

                        // Filling Bucket with Water
                        Canary.hooks().callHook(hook);
                        if (hook.isCanceled()) {
                            return itemstack;
                        }
                        //

                        if (--itemstack.b <= 0) {
                            return new ItemStack(Item.az);
                        }

                        if (!entityplayer.bn.a(new ItemStack(Item.az))) {
                            entityplayer.b(new ItemStack(Item.az.cv, 1, 0));
                        }

                        return itemstack;
                    }

                    if (world.g(i0, i1, i2) == Material.i && world.h(i0, i1, i2) == 0) {
                        world.i(i0, i1, i2);
                        if (entityplayer.bG.d) {
                            return itemstack;
                        }

                        // Filling Bucket with Lava
                        Canary.hooks().callHook(hook);
                        if (hook.isCanceled()) {
                            return itemstack;
                        }
                        //

                        if (--itemstack.b <= 0) {
                            return new ItemStack(Item.aA);
                        }

                        if (!entityplayer.bn.a(new ItemStack(Item.aA))) {
                            entityplayer.b(new ItemStack(Item.aA.cv, 1, 0));
                        }

                        return itemstack;
                    }
                } else {
                    if (this.a < 0) {
                        return new ItemStack(Item.ay);
                    }

                    if (movingobjectposition.e == 0) {
                        --i1;
                    }

                    if (movingobjectposition.e == 1) {
                        ++i1;
                    }

                    if (movingobjectposition.e == 2) {
                        --i2;
                    }

                    if (movingobjectposition.e == 3) {
                        ++i2;
                    }

                    if (movingobjectposition.e == 4) {
                        --i0;
                    }

                    if (movingobjectposition.e == 5) {
                        ++i0;
                    }

                    if (!entityplayer.a(i0, i1, i2, movingobjectposition.e, itemstack)) {
                        return itemstack;
                    }

                    if (this.a(world, i0, i1, i2) && !entityplayer.bG.d) {
                        return new ItemStack(Item.ay);
                    }
                }
            }

            return itemstack;
        }
    }

    public boolean a(World world, double d0, double d1, double d2, int i0, int i1, int i2) {
        return a(world, d0, d1, d2, i0, i1, i2, null); // CanaryMod: redirection
    }

    // CanaryMod: We need a Player for ItemUse
    public boolean a(World world, double d0, double d1, double d2, int i0, int i1, int i2, EntityPlayer entityplayer) {
        if (this.a <= 0) {
            return false;
        } else if (!world.c(i0, i1, i2) && world.g(i0, i1, i2).a()) {
            return false;
        } else {
            if (world.t.f && this.a == Block.F.cF) {
                world.a((double) ((float) i0 + 0.5F), (double) ((float) i1 + 0.5F), (double) ((float) i2 + 0.5F), "random.fizz", 0.5F, 2.6F + (world.s.nextFloat() - world.s.nextFloat()) * 0.8F);

                    for (int i3 = 0; i3 < 8; ++i3) {
                        world.a("largesmoke", (double) i0 + Math.random(), (double) i1 + Math.random(), (double) i2 + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
            } else {
                // CanaryMod: BlockPlaceHook water/lava bucket
                if (entityplayer != null) {
                    CanaryBlock clicked = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);
                    CanaryBlock placed = new CanaryBlock((short) this.a, (short) 0, i0, i1, i2, world.getCanaryWorld());
                    Player player = ((EntityPlayerMP) entityplayer).getPlayer();
                    BlockPlaceHook hook = new BlockPlaceHook(player, clicked, placed);

                    Canary.hooks().callHook(hook);
                    if (hook.isCanceled()) {
                        return false;
                    }
                }
                //
                world.f(i0, i1, i2, this.a, 0, 3);
            }

            return true;
        }
    }
}
