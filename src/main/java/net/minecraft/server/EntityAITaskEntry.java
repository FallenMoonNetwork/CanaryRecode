package net.minecraft.server;

public class EntityAITaskEntry { // CanaryMod: protected to public

    public EntityAIBase a;
    public int b;

    public final EntityAITasks c; // CanaryMod: protected to public

    public EntityAITaskEntry(EntityAITasks entityaitasks, int i0, EntityAIBase entityaibase) {
        this.c = entityaitasks;
        this.b = i0;
        this.a = entityaibase;
    }
}
