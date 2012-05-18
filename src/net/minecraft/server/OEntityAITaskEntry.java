package net.minecraft.server;

import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityAITasks;

class OEntityAITaskEntry {

    public OEntityAIBase a;
    public int b;
    // $FF: synthetic field
    final OEntityAITasks c;

    public OEntityAITaskEntry(OEntityAITasks var1, int var2, OEntityAIBase var3) {
        super();
        this.c = var1;
        this.b = var2;
        this.a = var3;
    }
}
