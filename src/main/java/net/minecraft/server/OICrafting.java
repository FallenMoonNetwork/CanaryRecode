package net.minecraft.server;

import java.util.List;
import net.minecraft.server.OContainer;
import net.minecraft.server.OItemStack;

public interface OICrafting {

    void a(OContainer var1, List var2);

    void a(OContainer var1, int var2, OItemStack var3);

    void a(OContainer var1, int var2, int var3);
}
