package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.Hook;
import net.canarymod.hook.player.RightClickHook;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemBlock extends OItem {

    private int a;

    public OItemBlock(int var1) {
        super(var1);
        this.a = var1 + 256;
        this.d(OBlock.m[var1 + 256].a(2));
    }

    public int a() {
        return this.a;
    }

    @Override
    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        // CanaryMod: Bail if we have nothing of the items in hand
        if (var1.a == 0) {
            return false;
        }
        
        // CanaryMod: Store blockInfo of the one we clicked
        Block blockClicked = var3.getCanaryDimension().getBlockAt(var4, var5, var6);
        
        int var8 = var3.a(var4, var5, var6);
        if (var8 == OBlock.aS.bO) {
            var7 = 1;
        } else if (var8 != OBlock.bu.bO && var8 != OBlock.X.bO && var8 != OBlock.Y.bO) {
            if (var7 == 0) {
                --var5;
            }

            if (var7 == 1) {
                ++var5;
            }

            if (var7 == 2) {
                --var6;
            }

            if (var7 == 3) {
                ++var6;
            }

            if (var7 == 4) {
                --var4;
            }

            if (var7 == 5) {
                ++var4;
            }
        }

        blockClicked.setFaceClicked(BlockFace.fromByte((byte)var7));
        
        if (var1.a == 0) {
            return false;
        } else if (!var2.d(var4, var5, var6)) {
            return false;
        } else if (var5 == 255 && OBlock.m[this.a].cd.a()) {
            return false;
        } else if (var3.a(this.a, var4, var5, var6, false, var7)) {
            // CanaryMod - Stop block place
            Block blockplace = new CanaryBlock((short)this.a, (byte)var1.h(), var4, var5, var6);
            RightClickHook hook = new RightClickHook(((OEntityPlayerMP)var2).getPlayer(), blockClicked, blockplace, var1.getCanaryItem(), null, Hook.Type.BLOCK_PLACE);
            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return false;
            }
            // CanaryMod - end
            OBlock var9 = OBlock.m[this.a];
            if (var3.b(var4, var5, var6, this.a, this.a(var1.h()))) {
                if (var3.a(var4, var5, var6) == this.a) {
                    OBlock.m[this.a].e(var3, var4, var5, var6, var7);
                    OBlock.m[this.a].a(var3, var4, var5, var6, (OEntityLiving) var2);
                }

                var3.a((var4 + 0.5F), (var5 + 0.5F), (var6 + 0.5F), var9.cb.c(), (var9.cb.a() + 1.0F) / 2.0F, var9.cb.b() * 0.8F);
                --var1.a;
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public String a(OItemStack var1) {
        return OBlock.m[this.a].q();
    }

    @Override
    public String b() {
        return OBlock.m[this.a].q();
    }
}
