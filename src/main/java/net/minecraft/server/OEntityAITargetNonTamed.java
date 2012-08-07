package net.minecraft.server;


import net.minecraft.server.OEntityAINearestAttackableTarget;
import net.minecraft.server.OEntityTameable;


public class OEntityAITargetNonTamed extends OEntityAINearestAttackableTarget {

    private OEntityTameable g;

    public OEntityAITargetNonTamed(OEntityTameable var1, Class var2, float var3, int var4, boolean var5) {
        super(var1, var2, var3, var4, var5);
        this.g = var1;
    }

    @Override
    public boolean a() {
        return this.g.u_() ? false : super.a();
    }
}
