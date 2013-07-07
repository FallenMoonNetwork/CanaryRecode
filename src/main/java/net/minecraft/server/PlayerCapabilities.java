package net.minecraft.server;

import net.canarymod.api.entity.living.humanoid.CanaryHumanCapabilities;

public class PlayerCapabilities {

    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e = true;
    public float f = 0.05F; // CanaryMod: private => public
    public float g = 0.1F; // CanaryMod: private => public
    private CanaryHumanCapabilities chc; // CanaryMod: wrapper instance

    public PlayerCapabilities() {
        this.chc = new CanaryHumanCapabilities(this); // CanaryMod: wrap instance
    }

    public void a(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();

        nbttagcompound1.a("invulnerable", this.a);
        nbttagcompound1.a("flying", this.b);
        nbttagcompound1.a("mayfly", this.c);
        nbttagcompound1.a("instabuild", this.d);
        nbttagcompound1.a("mayBuild", this.e);
        nbttagcompound1.a("flySpeed", this.f);
        nbttagcompound1.a("walkSpeed", this.g);
        nbttagcompound.a("abilities", (NBTBase) nbttagcompound1);
    }

    public void b(NBTTagCompound nbttagcompound) {
        if (nbttagcompound.b("abilities")) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.l("abilities");

            this.a = nbttagcompound1.n("invulnerable");
            this.b = nbttagcompound1.n("flying");
            this.c = nbttagcompound1.n("mayfly");
            this.d = nbttagcompound1.n("instabuild");
            if (nbttagcompound1.b("flySpeed")) {
                this.f = nbttagcompound1.g("flySpeed");
                this.g = nbttagcompound1.g("walkSpeed");
            }

            if (nbttagcompound1.b("mayBuild")) {
                this.e = nbttagcompound1.n("mayBuild");
            }
        }
    }

    public float a() {
        return this.f;
    }

    public float b() {
        return this.g;
    }

    public CanaryHumanCapabilities getCanaryCapabilities() {
        return chc;
    }
}
